package com.example.duan1_appdoctruyen.Model;

import java.io.Serializable;

public class Chap implements Serializable {
    String number,ngaydang,noidung;


    public Chap() {
    }

    public Chap(String number, String ngaydang,String noidung) {
        this.number = number;
        this.ngaydang = ngaydang;
        this.noidung = noidung;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
