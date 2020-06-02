package com.example.myselftravel3.AccountUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myselftravel3.ManagerAdmin.DiaDiem.DiaDiem;
import com.example.myselftravel3.ManagerAdmin.DoAn.ManagerAdminActivity_DoAn;
import com.example.myselftravel3.R;

import java.util.List;

public class AdapaterManagerUser extends RecyclerView.Adapter<AdapaterManagerUser.Holder> {
    Activity activity;
    List<DiaDiemUser> diaDiemUsers;

    public AdapaterManagerUser(Activity activity, List<DiaDiemUser> diaDiemUsers) {
        this.activity = activity;
        this.diaDiemUsers = diaDiemUsers;
    }

    @NonNull
    @Override
    public AdapaterManagerUser.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View row = layoutInflater.inflate(R.layout.activity_manager_user_diadiem,parent,false);
        return new AdapaterManagerUser.Holder(row);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapaterManagerUser.Holder holder, int position) {
        DiaDiemUser diaDiemUser = diaDiemUsers.get(position);
        holder.txt_idUserDiaDiem.setText(diaDiemUser.getIdDiaDiemUser()+"");
        holder.txt_tenUserDiaDiem.setText(diaDiemUser.getTenDiaDiemUser());
        holder.txt_Userphuongtienditoi.setText(diaDiemUser.getPhuongTienDiToiUser()+"");
        Bitmap bmPicture = BitmapFactory.decodeByteArray(diaDiemUser.getPictureUser(), 0, diaDiemUser.getPictureUser().length);
        holder.image.setImageBitmap(bmPicture);


        holder.btn_doandacsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ManagerAdminActivity_DoAn.class);
                activity.startActivity(intent);
            }
        });



    }


    @Override
    public int getItemCount(){
        return diaDiemUsers.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_idUserDiaDiem,txt_tenUserDiaDiem , txt_Userphuongtienditoi;
        Button btn_doandacsan;
        ImageView image;
        ImageButton immage_delete;


        public Holder(@NonNull View itemView) {
            super(itemView);
            immage_delete = (ImageButton) itemView.findViewById(R.id.image_btn_delete);
            txt_idUserDiaDiem = (TextView) itemView.findViewById(R.id.txt_manage_user_idDiaDiem);
            txt_tenUserDiaDiem = (TextView) itemView.findViewById(R.id.txt_manage_user_tenDiaDiem);
            txt_Userphuongtienditoi = (TextView) itemView.findViewById(R.id.txt_manage_user_phuongtienditoi);
            btn_doandacsan = (Button) itemView.findViewById(R.id.btn_manage_user_DoAn);
            image = (ImageView) itemView.findViewById(R.id.img_manage_menu_user);
        }
    }
}
