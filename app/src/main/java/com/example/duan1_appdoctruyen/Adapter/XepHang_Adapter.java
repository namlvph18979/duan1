package com.example.duan1_appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_appdoctruyen.Model.TruyenTranh;
import com.example.duan1_appdoctruyen.R;

import java.util.ArrayList;
import java.util.List;

public class XepHang_Adapter extends ArrayAdapter<TruyenTranh> {

    Context context;
    ArrayList<TruyenTranh> arrayList;
    TruyenTranh truyenTranh;

    public XepHang_Adapter(@NonNull Context context, int resource, ArrayList<TruyenTranh> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyentranh, null);
        }
        truyenTranh =  this.arrayList.get(position);
        TextView tentruyen = convertView.findViewById(R.id.tv_tentruyen);
        TextView Chap = convertView.findViewById(R.id.tv_chap);
        ImageView anh = convertView.findViewById(R.id.img_truyentranh);

        tentruyen.setText(truyenTranh.getTenTruyen());
        Chap.setText("Chap "+truyenTranh.getTenChap());
        anh.setImageResource(R.drawable.vidu);
//        }
        return convertView;
    }
}
