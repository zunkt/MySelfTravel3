package com.example.myselftravel3.ManagerAdmin.DiaDiem;

public class DiaDiem {
    private int idDiaDiem;
    private String TenDiaDiem;
    private String PhuongTienDiToi;
    private int idDoAn;
    private byte[] picture;

    public DiaDiem(int idDiaDiem, String tenDiaDiem, String phuongTienDiToi, int idDoAn, byte[] picture) {
        this.idDiaDiem = idDiaDiem;
        TenDiaDiem = tenDiaDiem;
        PhuongTienDiToi = phuongTienDiToi;
        this.idDoAn = idDoAn;
        this.picture = picture;
    }

    public DiaDiem(int idDiaDiem, String tenDiaDiem, String phuongTienDiToi, byte[] picture) {
        this.idDiaDiem = idDiaDiem;
        TenDiaDiem = tenDiaDiem;
        PhuongTienDiToi = phuongTienDiToi;
        this.picture = picture;
    }

    public DiaDiem(int idDiaDiem, String tenDiaDiem, String phuongTienDiToi) {
        this.idDiaDiem = idDiaDiem;
        TenDiaDiem = tenDiaDiem;
        PhuongTienDiToi = phuongTienDiToi;
    }

    public DiaDiem(int idDiaDiem, String tenDiaDiem, String phuongTienDiToi, int idDoAn) {
        this.idDiaDiem = idDiaDiem;
        TenDiaDiem = tenDiaDiem;
        PhuongTienDiToi = phuongTienDiToi;
        this.idDoAn = idDoAn;
    }



    public int getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(int idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getTenDiaDiem() {
        return TenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        TenDiaDiem = tenDiaDiem;
    }

    public String getPhuongTienDiToi() {
        return PhuongTienDiToi;
    }

    public void setPhuongTienDiToi(String phuongTienDiToi) {
        PhuongTienDiToi = phuongTienDiToi;
    }

    public int getIdDoAn() {
        return idDoAn;
    }

    public void setIdDoAn(int idDoAn) {
        this.idDoAn = idDoAn;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
