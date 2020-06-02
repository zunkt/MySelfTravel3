package com.example.myselftravel3.ManagerAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myselftravel3.MainActivity;
import com.example.myselftravel3.ManagerAdmin.DiaDiem.ManagerAdminActivity;
import com.example.myselftravel3.ManagerAdmin.DoAn.ManagerAdminActivity_DoAn;
import com.example.myselftravel3.R;

public class Activity_Admin_Menu extends AppCompatActivity {

    Button btn_managerdiadiem;
    Button btn_managerdoan;
    Button btn_manageruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__admin__menu);
        btn_managerdiadiem = (Button) findViewById(R.id.btn_manage_diadiem);
        btn_managerdoan = (Button)findViewById(R.id.btn_manager_dsmonan);
        btn_managerdiadiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Admin_Menu.this, ManagerAdminActivity.class);
                startActivity(intent);
            }
        });
        btn_managerdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Admin_Menu.this, ManagerAdminActivity_DoAn.class);
                startActivity(intent);
            }
        });
    }
}
