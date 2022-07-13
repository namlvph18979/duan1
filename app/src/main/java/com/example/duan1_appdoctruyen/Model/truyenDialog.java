package com.example.duan1_appdoctruyen.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_appdoctruyen.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class truyenDialog {

    int Image;
    String name,chap;

    public truyenDialog() {
    }

    public truyenDialog(int image, String name, String chap) {
        Image = image;
        this.name = name;
        this.chap = chap;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChap() {
        return chap;
    }

    public void setChap(String chap) {
        this.chap = chap;
    }

}
