package com.example.myselftravel3.ManagerAdmin.DoAn;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myselftravel3.ManagerAdmin.DiaDiem.DiaDiem;
import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;

import java.util.List;

public class AdapterManagerDoAn extends RecyclerView.Adapter<AdapterManagerDoAn.Holder> {

    Activity activity;
    List<DoAn> doAns;

    public AdapterManagerDoAn(Activity activity, List<DoAn> doAns) {
        this.activity = activity;
        this.doAns = doAns;
    }

    @NonNull
    @Override
    public AdapterManagerDoAn.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View row = layoutInflater.inflate(R.layout.activity_manager_do_an_info,parent,false);
        return new AdapterManagerDoAn.Holder(row);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterManagerDoAn.Holder holder, int position) {
        DoAn doAn = doAns.get(position);
        holder.txt_idDoAn.setText(doAn.getIdDoAn() + "");
        holder.txt_tenDoAn.setText(doAn.getTenDoAn());
        holder.txt_Diachi.setText(doAn.getDiaChi() + "");
        holder.txt_Giaca.setText(doAn.getGiaca() + "");
        Bitmap bmPicture = BitmapFactory.decodeByteArray(doAn.getPictureDoAn(), 0, doAn.getPictureDoAn().length);
        holder.image.setImageBitmap(bmPicture);
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Manager_AddDoAn.class);
                activity.startActivity(intent);
            }
        });

        holder.btn_deleteDoAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Ban muon xoa do an phai khong?");
                builder.setMessage("Ban co chac khong?");
                builder.setPositiveButton("Vang", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(doAn.getIdDoAn());
                    }
                });
                builder.setNegativeButton("No",null);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void delete(int idDoAn) {
        SQLiteDatabase database = MyDatabase.initDatabase(activity,"MySelfTravel.sqlite");
        database.delete("DoAn", "idDoAn = ?", new String[]{idDoAn + ""});
        for (DoAn doAn : doAns){
            if(doAn.getIdDoAn() == idDoAn){
                doAns.remove(doAn);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return doAns.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_idDoAn,txt_tenDoAn, txt_Diachi,txt_Giaca;
        Button btn_editDoAn , btn_deleteDoAn,btn_add;
        ImageView image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            txt_idDoAn = (TextView) itemView.findViewById(R.id.txt_manage_admin_idDoAn);
            txt_tenDoAn = (TextView) itemView.findViewById(R.id.txt_manage_admin_tenDoAn);
            txt_Diachi = (TextView) itemView.findViewById(R.id.txt_manage_admin_diachi);
            txt_Giaca = (TextView) itemView.findViewById(R.id.txt_manage_admin_giaca);
            btn_add = (Button) itemView.findViewById(R.id.btn_manage_admin_adddoan);
            btn_editDoAn = (Button) itemView.findViewById(R.id.btn_manage_admin_suadoan);
            btn_deleteDoAn = (Button) itemView.findViewById(R.id.btn_manage_admin_deleteDoAn);
            image = (ImageView) itemView.findViewById(R.id.img_doan);
        }
    }
}
