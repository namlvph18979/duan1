package com.example.duan1_appdoctruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.duan1_appdoctruyen.Model.truyenDialog;
import com.example.duan1_appdoctruyen.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class listxoa_Adapter extends ArrayAdapter<truyenDialog> {

    private Context context;
    private ArrayList<truyenDialog> arrayList;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public listxoa_Adapter( Context context,ArrayList<truyenDialog> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arrayList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview, null);
        }

        truyenDialog truyenDialog = this.arrayList.get(position);

        SwipeRevealLayout swipeRevealLayout = convertView.findViewById(R.id.swiper);
        ImageView img_delete = convertView.findViewById(R.id.img_delete);
        ImageView img_edit = convertView.findViewById(R.id.img_edit);

        EditText edt_timtruyenxoa = convertView.findViewById(R.id.edt_timtruyen_xoa);


        CircleImageView imageView = convertView.findViewById(R.id.img_itemlistview_xoa);
        imageView.setImageResource(truyenDialog.getImage());

        TextView tv_tentruyen = convertView.findViewById(R.id.tv_lvxoa_tentruyen);
        tv_tentruyen.setText("Ten: "+truyenDialog.getName());

        TextView tv_chap = convertView.findViewById(R.id.tv_lvxoa_chap);
        tv_chap.setText("Chap: "+truyenDialog.getChap());


        viewBinderHelper.bind(swipeRevealLayout,truyenDialog.getName());

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }


}
