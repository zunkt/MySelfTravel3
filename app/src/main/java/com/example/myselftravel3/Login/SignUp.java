package com.example.myselftravel3.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myselftravel3.Account;
import com.example.myselftravel3.NetworkUser.AccountAPI;
import com.example.myselftravel3.R;
import com.google.android.material.textfield.TextInputEditText;
import static com.example.myselftravel3.MainActivity.accounts;

public class SignUp extends AppCompatActivity {

    //TXP , Button , TextView
    TextInputEditText txp_username , txp_password , txp_email;
    Button btn_save , btn_cancel;
    //CODE
    private final String SIGN_IN = "SIGNUP";
    private final String Account_List = "USERLIST";

    //SUCCESS
    boolean CODE_SUCCESS = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        addControls();
        addEvent();
    }

    private void addEvent() {
        for( Account user : accounts){
            Log.e("TAG : ","SIGN" + user.toString());
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG :","CANCEL");
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }
    private void addUser(){
        CODE_SUCCESS = true;
        String username = txp_username.getText().toString();
        String password = txp_password.getText().toString();
        if (username.isEmpty()) {
            CODE_SUCCESS = false;
            txp_username.setError("Username is inValid");
        }else {
            for (Account user : accounts) {
                if (user.getName().equals(username) || username.equals("admin")) {
                    CODE_SUCCESS = false;
                    txp_username.setError("Username Da Ton Tai");
                }
            }
        }
        if (password.isEmpty()) {
            txp_password.setError("Password khong hop le.");
        }
        if(CODE_SUCCESS && !password.isEmpty()) {
            CODE_SUCCESS = true;
            Account user = new Account(username, password);
            Intent intent = new Intent();
            intent.putExtra(SIGN_IN, user);
            setResult(RESULT_OK, intent);
            Log.e("TAG :", "SIGN IN -> MAIN");
            Log.e("SUCCES :",CODE_SUCCESS +"");
            finish();
        }
    }

    private void addControls() {
        txp_username = (TextInputEditText) findViewById(R.id.txt_signup_username);
        txp_password = (TextInputEditText) findViewById(R.id.txt_signup_password);
        btn_save = (Button) findViewById(R.id.btn_signup_save);
        btn_cancel = (Button) findViewById(R.id.btn_signup_cancel);
    }
}
