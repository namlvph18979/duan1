package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.duan1_appdoctruyen.Adapter.chap_Adapter;
import com.example.duan1_appdoctruyen.Model.Chap;

import java.util.ArrayList;

public class Truyen_Activity extends AppCompatActivity {

    ImageView img_back,img_heart;
    ListView listView;
    Chap chap = new Chap();
    chap_Adapter adapter;
    ArrayList<Chap> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        img_back = findViewById(R.id.img_back);
        img_heart = findViewById(R.id.img_like);
        listView = findViewById(R.id.lv_listchaptruyen);

        list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(new Chap("chap."+i,"10/8/2021"));
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Animatoo.animateSlideRight(Truyen_Activity.this);
            }
        });

        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    img_heart.setImageResource(R.drawable.heartnew2);
                    Toast.makeText(getApplicationContext(),"Đã thích truyện",Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new chap_Adapter(getApplicationContext(),list);
        listView.setAdapter(adapter);


    }
}