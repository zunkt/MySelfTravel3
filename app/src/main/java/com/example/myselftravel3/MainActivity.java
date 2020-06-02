package com.example.myselftravel3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myselftravel3.AccountUser.Activity_account_user;
import com.example.myselftravel3.Login.SignUp;
import com.example.myselftravel3.ManagerAdmin.Activity_Admin_Menu;
import com.example.myselftravel3.ManagerAdmin.DiaDiem.ManagerAdminActivity;
import com.example.myselftravel3.NetworkUser.AccountAPI;
import com.example.myselftravel3.NetworkUser.AddResult;
import com.example.myselftravel3.NetworkUser.LoadResult;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnSignup;
    private EditText txt_user;
    private  EditText txt_pass;
    private String username;
    private String password;
    public static List<Account> accounts = new ArrayList<Account>();
    private final int CODE_ADMIN = 1;
    private final int CODE_USER = 2;
    private final int CODE_SIGN_UP = 999;

    //API
    private AccountAPI accountAPI;
    //bien check du lieu
    private boolean dataloaded = false;
    private boolean dataloading = false;
    private boolean login = false;
    private DatabaseDAO db;
    private AccountDAO accountDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addAPI();
        loadUsers();
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ManagerAdminActivity.class);
//                startActivity(intent);
                txt_user = findViewById(R.id.txt_username);
                txt_pass = findViewById(R.id.txt_password);
                username = txt_user.getText().toString();
                password = txt_pass.getText().toString();
                if(username.equals("admin") && password.equals("admin")) {
                    Log.e("TAG :", "Dang Nhap Thanh Cong!");
                    loginSuccess();
                    login = true;
                }
                else {
                    for(Account account : accounts){
                        if (account.getName().equals(username) && account.getPassword().equals(password)){
                            Log.e("TAG :","Dang Nhap Thanh Cong!");
                            loginSuccess_User();
                            login = true;
                            break;
                        }
//                        else {
//                            Toast toast=Toast.makeText(MainActivity.this,"Ten tai khoan hoac mat khau khong hop le",   Toast.LENGTH_SHORT);
//                            toast.show();
////                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
////                            builder.setTitle("Ten tai khoan hoac mat khau khong hop le");
////                            Dialog dialog = builder.create();
////                            dialog.show();
//                        }
                    }
                }


            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivityForResult(intent,CODE_SIGN_UP);
            }
        });
    }

    private void loginSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dang Nhap Thanh Cong");
        builder.setMessage("Wellcome Admin!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, Activity_Admin_Menu.class);
                startActivityForResult(intent,CODE_ADMIN);
                finish();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
    private void loginSuccess_User() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dang Nhap Thanh Cong");
        builder.setMessage("Wellcome!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, Activity_account_user.class);
                startActivityForResult(intent,CODE_USER);
                finish();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void addAPI() {
        accountAPI = API.getAccountAPI();
    }

    private void loadUsers() {
        dataloading = true;
        Call<LoadResult> call = accountAPI.getUsers();
        call.enqueue(new Callback<LoadResult>() {
            @Override
            public void onResponse(Call<LoadResult> call, Response<LoadResult> response) {
                if(response.isSuccessful()){
                    LoadResult result = response.body();
                    if(result != null && result.status){
                        dataloaded = true;
                        dataloading = false;
                        List<Account> accountList = result.accounts;
                        if (!accounts.isEmpty()) {
                            accounts.clear();
                        }
                        accounts.addAll(accountList);
                    }else{
                        Log.e("TAG","Server FAil" + result.message);
                    }
                }else{
                    Log.e("TAG","FAIL" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoadResult> call, Throwable t) {
                dataloading = false;
                t.printStackTrace();
            }
        });
    }

    private void addAccount(Account account) {
        accountAPI.addUser(account.getName(),account.getPassword())
                .enqueue(new Callback<AddResult>() {
                    @Override
                    public void onResponse(Call<AddResult> call, Response<AddResult> response) {
                        AddResult addResult = response.body();
                        if(addResult.status){
                            int id = addResult.id;
                            account.setId(id);
                            Log.e("TAG"," Success" + addResult.id);
                        }else {
                            Log.e("TAG", " Fail" + addResult.message);
                        }
                    }

                    @Override
                    public void onFailure(Call<AddResult> call, Throwable t) {
                        Log.e("TAG","ADD USER FAIL" + t.getMessage());
                        t.printStackTrace();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_SIGN_UP && resultCode == RESULT_OK){
            Account account = data.getParcelableExtra("SIGNUP");
            accounts.add(account);
            int id = (int) accountDAO.insert(account);
            account.setId(id);
            Log.e("TAG : ","CHECK USER ID :" + account.getId());
            addAccount(account);
        }else{
            Log.e("TAG :","MAIN take SIGN FAIL ");
        }
    }

    private void addControls() {
        //RoomDatabase
        db = Room.databaseBuilder(this,DatabaseDAO.class,"DatabaseDAO").allowMainThreadQueries().build();
        accountDAO = db.getAccountDAO();
        accounts = accountDAO.getAllAccount();
    }
}
