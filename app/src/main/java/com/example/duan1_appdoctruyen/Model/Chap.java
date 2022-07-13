package com.example.duan1_appdoctruyen.Model;

public class Chap {
    String number,ngaydang;;


    public Chap() {
    }

    public Chap(String number, String ngaydang) {
        this.number = number;
        this.ngaydang = ngaydang;
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
}
