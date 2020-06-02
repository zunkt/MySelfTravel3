package com.example.myselftravel3.ManagerAdmin.DiaDiem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myselftravel3.ManagerAdmin.DoAn.ManagerAdminActivity_DoAn;
import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;
import com.google.android.material.textfield.TextInputEditText;
import static com.example.myselftravel3.R.drawable.grey;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import static com.example.myselftravel3.ManagerAdmin.DiaDiem.ManagerAdminActivity.diadiemList;

public class Manager_AddDiaDiem extends AppCompatActivity {

    //CODE
    private final int REQUEST_CHOOSE_PHOTO = 15;
    private final String MENU_ADD = "MENUADD";
    //TXP Button Image
    TextInputEditText txt_idDiaDiem , txt_tenDiaDiem , txt_phuongtienditoi ;
    Button  btn_choose , btn_save ;
    ImageView image;
    //CHECK
    boolean success = true;
    int idDiaDiem;
    byte[] picture;
    boolean photo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__adddiadiem);
        addControls();
        addEvent();
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void addControls(){
        txt_idDiaDiem = (TextInputEditText) findViewById(R.id.txd_add_diadiem_id);
        txt_tenDiaDiem = (TextInputEditText) findViewById(R.id.txt_add_diadiem_name);
        txt_phuongtienditoi = (TextInputEditText) findViewById(R.id.txt_add_diadiem_phuongtienditoi);
        btn_choose = (Button) findViewById(R.id.btn_choose_image_diadiem);
        btn_save = (Button) findViewById(R.id.btn_add_menu_save);
        image = (ImageView) findViewById(R.id.img_manage_menu_add_admin);
    }
    private void addEvent() {
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenu();
            }
        });
    }

    private void addMenu() {
        success = true;
        String id = txt_idDiaDiem.getText().toString();
        String tendiadiem = txt_tenDiaDiem.getText().toString();
        String phuongtienditoi = txt_phuongtienditoi.getText().toString();
        if(id.isEmpty()){
            txt_idDiaDiem.setError("ID is invalid");
        }else{
            idDiaDiem = Integer.parseInt(id);
            for(int i = 0; i < diadiemList.size(); i++) {
                if (diadiemList.get(i).getIdDiaDiem() == idDiaDiem) {
                    txt_idDiaDiem.setError("ID already exists");
                    success = false;
                    break;
                }
            }
        }
        if(tendiadiem.isEmpty()){
            success = false;
            txt_tenDiaDiem.setError("Ten Dia Diem khong hop le");
        }
        if(phuongtienditoi.isEmpty()){
            success = false;
            txt_phuongtienditoi.setError("Phuong tien di toi khong hop le");
        }
        if(!photo){
//            image.setImageResource(grey);
            picture = getByteArrayFromImageView(image);
        }else {
            picture = getByteArrayFromImageView(image);
            photo = false;
        }
        if(success && !tendiadiem.isEmpty()){
            DiaDiem diaDiem = new DiaDiem(idDiaDiem, tendiadiem, phuongtienditoi, picture);
            //Add on SQLITE
            ContentValues contentValues = new ContentValues();
            contentValues.put("idDiaDiem", diaDiem.getIdDiaDiem());
            contentValues.put("TenDiaDiem", diaDiem.getTenDiaDiem());
            contentValues.put("PhuongTienDiToi", diaDiem.getPhuongTienDiToi());
            contentValues.put("idDoAn", diaDiem.getIdDoAn());
            contentValues.put("pictures", diaDiem.getPicture());

            SQLiteDatabase database = MyDatabase.initDatabase(this, "MySelfTravel.sqlite");
            database.insert("DiaDiem", null, contentValues);
//            Intent intent = new Intent(this,ActivityManageMenu.class);
            Intent intent = new Intent(this, ManagerAdminActivity.class);
            startActivity(intent);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CHOOSE_PHOTO){
                try {
                    photo = true;
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    image.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
