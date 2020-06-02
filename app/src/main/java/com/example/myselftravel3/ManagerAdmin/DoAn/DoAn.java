package com.example.myselftravel3.ManagerAdmin.DoAn;

import java.util.Arrays;

public class DoAn {
    private int idDoAn;
    private String TenDoAn;
    private String DiaChi;
    private String giacadoan;
    private byte[] pictureDoAn;

    public DoAn(int idDoAn, String tenDoAn, String diaChi, String giaca, byte[] pictureDoAn) {
        this.idDoAn = idDoAn;
        TenDoAn = tenDoAn;
        DiaChi = diaChi;
        this.giacadoan = giaca;
        this.pictureDoAn = pictureDoAn;
    }


    public int getIdDoAn() {
        return idDoAn;
    }

    public void setIdDoAn(int idDoAn) {
        this.idDoAn = idDoAn;
    }

    public String getTenDoAn() {
        return TenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        TenDoAn = tenDoAn;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getGiaca() {
        return giacadoan;
    }

    public void setGiaca(String giaca) {
        this.giacadoan = giaca;
    }

    public byte[] getPictureDoAn() {
        return pictureDoAn;
    }

    public void setPictureDoAn(byte[] pictureDoAn) {
        this.pictureDoAn = pictureDoAn;
    }

    @Override
    public String toString() {
        return "DoAn{" +
                "idDoAn=" + idDoAn +
                ", TenDoAn='" + TenDoAn + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", giaca=" + giacadoan +
                ", pictureDoAn=" + Arrays.toString(pictureDoAn) +
                '}';
    }
}