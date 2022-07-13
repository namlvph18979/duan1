package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.duan1_appdoctruyen.Adapter.listxoa_Adapter;
import com.example.duan1_appdoctruyen.Model.truyenDialog;

import java.util.ArrayList;

public class Delete_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete);

        ImageView img_remove;
        EditText findDelete;
        ListView lis1;
        listxoa_Adapter adapter2;
        ArrayList<truyenDialog> list2;
        truyenDialog truyenDialog = new truyenDialog();

        img_remove = findViewById(R.id.img_remove);
        findDelete = findViewById(R.id.edt_timtruyen_xoa);
        lis1 = findViewById(R.id.lv_xoatruyen);

            list2 = new ArrayList<truyenDialog>();
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));
            list2.add(new truyenDialog(R.drawable.ngontinh,"truyen nhu cai quan que","196"));

            adapter2 = new listxoa_Adapter(getApplicationContext(),list2);
            lis1.setAdapter(adapter2);

            findDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            img_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            });

    }



}