package com.example.myselftravel3.ManagerAdmin.DiaDiem;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myselftravel3.ManagerAdmin.DoAn.ManagerAdminActivity_DoAn;
import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;

import java.util.List;

public class AdapterManagerAdmin extends RecyclerView.Adapter<AdapterManagerAdmin.Holder>{

    Activity activity;
    List<DiaDiem> diaDiems;

    public AdapterManagerAdmin(Activity activity, List<DiaDiem> diaDiems) {
        this.activity = activity;
        this.diaDiems = diaDiems;
    }

    @NonNull
    @Override
    public AdapterManagerAdmin.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View row = layoutInflater.inflate(R.layout.activity_manager_admin_diadiem_info,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterManagerAdmin.Holder holder, int position) {
        DiaDiem diaDiem = diaDiems.get(position);
        holder.txt_idDiaDiem.setText(diaDiem.getIdDiaDiem()+"");
        holder.txt_tenDiaDiem.setText(diaDiem.getTenDiaDiem());
        holder.txt_phuongtienditoi.setText(diaDiem.getPhuongTienDiToi()+"");
        Bitmap bmPicture = BitmapFactory.decodeByteArray(diaDiem.getPicture(), 0, diaDiem.getPicture().length);
        holder.image.setImageBitmap(bmPicture);

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Manager_AddDiaDiem.class);
                activity.startActivity(intent);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Delete Menu");
                builder.setMessage("Are you sure");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(diaDiem.getIdDiaDiem());
                    }
                });
                builder.setNegativeButton("No",null);
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.btn_doandacsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ManagerAdminActivity_DoAn.class);
                activity.startActivity(intent);
            }
        });
    }

    private void delete(int idDiaDiem) {
        SQLiteDatabase database = MyDatabase.initDatabase(activity,"MySelfTravel.sqlite");
        database.delete("DiaDiem", "idDiaDiem = ?", new String[]{idDiaDiem + ""});
        for (DiaDiem diaDiem : diaDiems){
            if(diaDiem.getIdDiaDiem() == idDiaDiem){
                diaDiems.remove(diaDiem);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return diaDiems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_idDiaDiem,txt_tenDiaDiem , txt_phuongtienditoi;
        Button btn_edit,btn_delete,btn_doandacsan, btn_add;
        ImageView image;


        public Holder(@NonNull View itemView) {
            super(itemView);
            txt_idDiaDiem = (TextView) itemView.findViewById(R.id.txt_manage_admin_idDiaDiem);
            txt_tenDiaDiem = (TextView) itemView.findViewById(R.id.txt_manage_admin_tenDiaDiem);
            txt_phuongtienditoi = (TextView) itemView.findViewById(R.id.txt_manage_admin_phuongtienditoi);
            btn_doandacsan = (Button) itemView.findViewById(R.id.btn_manage_admin_DoAn);
            btn_add = (Button) itemView.findViewById(R.id.btn_manage_admin_ADD);
            btn_delete = (Button) itemView.findViewById(R.id.btn_manage_admin_Delete);
            image = (ImageView) itemView.findViewById(R.id.img_manage_menu);
        }
    }
}
