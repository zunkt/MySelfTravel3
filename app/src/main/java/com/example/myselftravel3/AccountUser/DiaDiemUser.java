package com.example.myselftravel3.AccountUser;

public class DiaDiemUser {
    private int idDiaDiemUser;
    private String TenDiaDiemUser;
    private String PhuongTienDiToiUser;
    private int idDoAnUser;
    private byte[] pictureUser;

    public DiaDiemUser(int idDiaDiemUser, String tenDiaDiemUser, String phuongTienDiToiUser, int idDoAnUser, byte[] pictureUser) {
        this.idDiaDiemUser = idDiaDiemUser;
        TenDiaDiemUser = tenDiaDiemUser;
        PhuongTienDiToiUser = phuongTienDiToiUser;
        this.idDoAnUser = idDoAnUser;
        this.pictureUser = pictureUser;
    }

    public DiaDiemUser(int idDiaDiemUser, String tenDiaDiemUser, String phuongTienDiToiUser, byte[] pictureUser) {
        this.idDiaDiemUser = idDiaDiemUser;
        TenDiaDiemUser = tenDiaDiemUser;
        PhuongTienDiToiUser = phuongTienDiToiUser;
        this.pictureUser = pictureUser;
    }

    public int getIdDiaDiemUser() {
        return idDiaDiemUser;
    }

    public void setIdDiaDiemUser(int idDiaDiemUser) {
        this.idDiaDiemUser = idDiaDiemUser;
    }

    public String getTenDiaDiemUser() {
        return TenDiaDiemUser;
    }

    public void setTenDiaDiemUser(String tenDiaDiemUser) {
        TenDiaDiemUser = tenDiaDiemUser;
    }

    public String getPhuongTienDiToiUser() {
        return PhuongTienDiToiUser;
    }

    public void setPhuongTienDiToiUser(String phuongTienDiToiUser) {
        PhuongTienDiToiUser = phuongTienDiToiUser;
    }

    public int getIdDoAnUser() {
        return idDoAnUser;
    }

    public void setIdDoAnUser(int idDoAnUser) {
        this.idDoAnUser = idDoAnUser;
    }

    public byte[] getPictureUser() {
        return pictureUser;
    }

    public void setPictureUser(byte[] pictureUser) {
        this.pictureUser = pictureUser;
    }
}
