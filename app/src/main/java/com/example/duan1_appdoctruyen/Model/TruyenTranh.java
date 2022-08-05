package com.example.duan1_appdoctruyen.Model;

import java.io.Serializable;

public class TruyenTranh implements Serializable {

    private String tenTruyen,tenChap,theloai,luotview,luotthich;


    public TruyenTranh() {
    }

    public TruyenTranh(String tenTruyen, String tenChap, String theloai, String luotview, String luotthich) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.theloai = theloai;
        this.luotview = luotview;
        this.luotthich = luotthich;
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

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getLuotview() {
        return luotview;
    }

    public void setLuotview(String luotview) {
        this.luotview = luotview;
    }

    public String getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(String luotthich) {
        this.luotthich = luotthich;
    }
}
