package com.example.myselftravel3.ManagerAdmin.DoAn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myselftravel3.ManagerAdmin.DiaDiem.DiaDiem;
import com.example.myselftravel3.ManagerAdmin.DiaDiem.ManagerAdminActivity;
import com.example.myselftravel3.MyDatabase;
import com.example.myselftravel3.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.myselftravel3.ManagerAdmin.DoAn.ManagerAdminActivity_DoAn.doAnList;

public class Manager_AddDoAn extends AppCompatActivity {

    //CODE
    private final int REQUEST_CHOOSE_PHOTO = 50;
    private final String MENU_ADD = "MENUADD";
    //TXP Button Image
    TextInputEditText txt_idDoAn, txt_tenDoAn,txt_diachidoan,txt_giaca;
    Button btn_choose , btn_save ;
    ImageView image;
    //CHECK
    boolean success = true;
    int idDoAn;
    byte[] picture;
    boolean photo = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__add_do_an);
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
        txt_idDoAn = (TextInputEditText) findViewById(R.id.txd_add_doan_id);
        txt_tenDoAn = (TextInputEditText) findViewById(R.id.txt_add_doan_name);
        txt_diachidoan = (TextInputEditText) findViewById(R.id.txt_add_diachi_doan);
        txt_giaca = (TextInputEditText) findViewById(R.id.txt_add_giaca_doan);
        btn_choose = (Button) findViewById(R.id.btn_choose_image_doan);
        btn_save = (Button) findViewById(R.id.btn_add_doan_save);
        image = (ImageView) findViewById(R.id.img_manage_menu_add_doan);
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
        String id = txt_idDoAn.getText().toString();
        String tendoan = txt_tenDoAn.getText().toString();
        String diachidoan = txt_diachidoan.getText().toString();
        String giacadoan = txt_giaca.getText().toString();
        if(id.isEmpty()){
            txt_idDoAn.setError("ID is invalid");
        }else{
            idDoAn = Integer.parseInt(id);
            for(int i = 0; i < doAnList.size(); i++) {
                if (doAnList.get(i).getIdDoAn() == idDoAn) {
                    txt_idDoAn.setError("ID already exists");
                    success = false;
                    break;
                }
            }
        }
        if(tendoan.isEmpty()){
            success = false;
            txt_tenDoAn.setError("Ten do an khong hop le");
        }
        if(diachidoan.isEmpty()){
            success = false;
            txt_diachidoan.setError("Dia chi do an khong hop le");
        }
        if(giacadoan.isEmpty()){
            success = false;
            txt_giaca.setError("Gia ca do an khong hop le");
        }
        if(!photo){
//            image.setImageResource(grey);
            picture = getByteArrayFromImageView(image);
        }else {
            picture = getByteArrayFromImageView(image);
            photo = false;
        }
        if(success && !tendoan.isEmpty()){
            DoAn doAn = new DoAn(idDoAn,tendoan,diachidoan,giacadoan, picture);
            //Add on SQLITE
            ContentValues contentValues = new ContentValues();
            contentValues.put("idDoAn", doAn.getIdDoAn());
            contentValues.put("TenDoAn", doAn.getTenDoAn());
            contentValues.put("DiaChi", doAn.getDiaChi());
            contentValues.put("GiaCa", doAn.getGiaca());
            contentValues.put("pictureDoAn", doAn.getPictureDoAn());

            SQLiteDatabase database = MyDatabase.initDatabase(this, "MySelfTravel.sqlite");
            database.insert("DoAn", null, contentValues);
//            Intent intent = new Intent(this,ActivityManageMenu.class);
            Intent intent = new Intent(this, ManagerAdminActivity_DoAn.class);
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
