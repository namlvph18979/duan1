package com.example.duan1_appdoctruyen.Model;

import java.io.Serializable;

public class TruyenTranh implements Serializable {

    private String tenTruyen,tenChap,theloai,luotview,luotthich,id,img;




    public TruyenTranh() {
    }

    public TruyenTranh(String tenTruyen, String tenChap, String theloai, String luotview, String luotthich,String id,String img) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.theloai = theloai;
        this.luotview = luotview;
        this.luotthich = luotthich;
        this.id = id;
        this.img = img;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
