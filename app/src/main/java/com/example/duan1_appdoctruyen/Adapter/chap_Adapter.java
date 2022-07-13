package com.example.duan1_appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1_appdoctruyen.Model.Chap;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;
import com.example.duan1_appdoctruyen.R;

import java.util.ArrayList;
import java.util.List;

public class chap_Adapter extends ArrayAdapter<Chap> {
    ArrayList<Chap> tranhList;
    Context context;

    public chap_Adapter(@NonNull Context context, @NonNull List<Chap> objects) {
        super(context, 0, objects);
        this.context = context;
        this.tranhList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chap_truyen,null);
        }

        Chap chap = this.tranhList.get(position);

        TextView tv_tenchap = convertView.findViewById(R.id.tv_chapnumber);
        TextView tv_ngay = convertView.findViewById(R.id.tv_ngaydang);

        tv_tenchap.setText(chap.getNumber());
        tv_ngay.setText(chap.getNgaydang());

        return convertView;
    }
}
