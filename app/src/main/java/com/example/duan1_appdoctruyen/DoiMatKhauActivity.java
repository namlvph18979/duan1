package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class DoiMatKhauActivity extends AppCompatActivity {
    TextView dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        dangnhap = findViewById(R.id.tv_dmk_dangnhap);

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoiMatKhauActivity.this,LoginActivity.class));
                Animatoo.animateSlideLeft(DoiMatKhauActivity.this);
            }
        });
    }
}