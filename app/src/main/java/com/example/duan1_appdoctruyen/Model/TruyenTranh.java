package com.example.duan1_appdoctruyen.Model;

public class TruyenTranh {

    private String tenTruyen,tenChap;
    private int anh;

    public TruyenTranh() {
    }

    public TruyenTranh(String tenTruyen, String tenChap, int anh) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.anh = anh;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

}
