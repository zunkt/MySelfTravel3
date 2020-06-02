package com.example.myselftravel3.ManagerAdmin.DoAn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;

import java.util.ArrayList;
import java.util.List;

public class ManagerAdminActivity_DoAn extends AppCompatActivity {

    //DATABASE
    public static final String DATABASE_NAME = "MySelfTravel.sqlite";
    SQLiteDatabase database;
    RecyclerView recyclerView;
    AdapterManagerDoAn adapterManagerDoAn;
    public static List<DoAn> doAnList  = new ArrayList<DoAn>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_admin__do_an);
        addControls();
        readData();
    }
    @SuppressLint("WrongConstant")
    private void addControls() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Manager_Admin_DoAn);
        adapterManagerDoAn = new AdapterManagerDoAn(ManagerAdminActivity_DoAn.this,doAnList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ManagerAdminActivity_DoAn.this, LinearLayout.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapterManagerDoAn);
    }

    private void readData() {
        database = MyDatabase.initDatabase(this , DATABASE_NAME);
        Cursor checkData = database.rawQuery("SELECT * FROM DoAn ORDER BY idDoAn",null);
        doAnList.clear();
        for (int i=0 ; i < checkData.getCount() ; i++){
            checkData.moveToPosition(i);
            int idDoAn = checkData.getInt(0);
            String TenDoAn = checkData.getString(1);
            String DiaChi = checkData.getString(2);
            String GiaCa = checkData.getString(3);
            byte[] hinhanhdoan = checkData.getBlob(4);
            doAnList.add(new DoAn(idDoAn,TenDoAn,DiaChi,GiaCa,hinhanhdoan));
        }
        adapterManagerDoAn.notifyDataSetChanged();
    }
}
