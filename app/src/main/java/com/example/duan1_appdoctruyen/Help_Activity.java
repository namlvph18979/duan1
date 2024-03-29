package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Help_Activity extends AppCompatActivity {

    EditText edtphanhoi;
    ImageView img_back;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        edtphanhoi = findViewById(R.id.edt_phanhoi);
        img_back = findViewById(R.id.img_back);
        btn_send = findViewById(R.id.btn_phanhoi);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtphanhoi.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập thông tin phản hồi",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(getApplicationContext(),"Đã gửi phản hồi",Toast.LENGTH_SHORT).show();
                }

            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}