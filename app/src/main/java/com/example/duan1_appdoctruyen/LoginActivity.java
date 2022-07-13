package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class LoginActivity extends AppCompatActivity {

    TextView dangky,khach,doimk;
    EditText edt_username,edt_password;
    Button btn_login,btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        khach = findViewById(R.id.tv_khach);
        dangky = findViewById(R.id.tv_dangky);
        doimk = findViewById(R.id.tv_doimatkhau);

        edt_password = findViewById(R.id.edt_password);
        edt_username = findViewById(R.id.edt_username);

        btn_login = findViewById(R.id.btn_login);
        btn_cancel = findViewById(R.id.btn_cancel);


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_username.setText("");
                edt_password.setText("");
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Dangky_Activity.class));
                Animatoo.animateSlideLeft(LoginActivity.this);
            }
        });

        khach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                Animatoo.animateZoom(LoginActivity.this);
            }
        });

        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,DoiMatKhauActivity.class));
                Animatoo.animateSlideRight(LoginActivity.this);
            }
        });

    }


    public void validate(){
        String user = edt_username.getText().toString();
        String pass = edt_password.getText().toString();

        if (user.isEmpty() || pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            return;
        }else if (user.equals("admin") && pass.equals("admin") ){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            Animatoo.animateZoom(LoginActivity.this);
            return;
        }
    }
}