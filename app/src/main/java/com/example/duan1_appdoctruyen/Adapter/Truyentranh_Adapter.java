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

public class Truyentranh_Adapter extends ArrayAdapter<TruyenTranh> {

    private Context context;
    private ArrayList<TruyenTranh> arrayList;

    public Truyentranh_Adapter(Context context, int resource, List<TruyenTranh> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyentranh,null);
        }

//        if (arrayList.size()>0){
            TruyenTranh truyenTranh =  this.arrayList.get(position);
            TextView tentruyen = convertView.findViewById(R.id.tv_tentruyen);
            TextView Chap = convertView.findViewById(R.id.tv_chap);
            ImageView anh = convertView.findViewById(R.id.img_truyentranh);

            tentruyen.setText("Nghịch thiên tà thần");
            Chap.setText("Chap 889");
            anh.setImageResource(R.drawable.vidu);
//        }
        return convertView;
    }
}
