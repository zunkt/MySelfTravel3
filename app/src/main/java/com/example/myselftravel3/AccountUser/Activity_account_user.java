package com.example.myselftravel3.AccountUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.myselftravel3.ManagerAdmin.DiaDiem.AdapterManagerAdmin;
import com.example.myselftravel3.ManagerAdmin.DiaDiem.DiaDiem;
import com.example.myselftravel3.ManagerAdmin.DiaDiem.ManagerAdminActivity;
import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_account_user extends AppCompatActivity {

    //DATABASE
    public static final String DATABASE_NAME = "MySelfTravel.sqlite";
    SQLiteDatabase database;
    RecyclerView recyclerView;
    AdapaterManagerUser adapterManagerUser;
    List<DiaDiemUser> diaDiemUserList  = new ArrayList<DiaDiemUser>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_user);
        addControls();
        readData();
    }


    @SuppressLint("WrongConstant")
    private void addControls() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Manager_User);
        adapterManagerUser = new AdapaterManagerUser(Activity_account_user.this,diaDiemUserList);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_account_user.this, LinearLayout.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapterManagerUser);
    }

    private void readData() {
        database = MyDatabase.initDatabase(this , DATABASE_NAME);
        Cursor checkData = database.rawQuery("SELECT * FROM DiaDiem ORDER BY IdDiaDiem",null);
        diaDiemUserList.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            int idDiaDiem = checkData.getInt(0);
            String TenDiaDiem = checkData.getString(1);
            String PhuongTienDiToi = checkData.getString(2);
            int idDoAn = checkData.getInt(3);
            byte[] picture = checkData.getBlob(4);
            diaDiemUserList.add(new DiaDiemUser(idDiaDiem,TenDiaDiem,PhuongTienDiToi,idDoAn,picture));
        }
        adapterManagerUser.notifyDataSetChanged();
    }
}
