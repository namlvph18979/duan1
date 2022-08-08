package com.example.duan1_appdoctruyen.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;

import java.util.ArrayList;
import java.util.List;

public class Top_Adapter extends ArrayAdapter<TruyenTranh> {

    private Context context;
    private ArrayList<TruyenTranh> arrayList;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public Top_Adapter(@NonNull Context context, int resource, @NonNull List<TruyenTranh> objects) {
        super(context, resource, objects);
    }
}
