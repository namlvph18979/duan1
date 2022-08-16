package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1_appdoctruyen.Model.nguoidung;

public class Information_Activity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    TextView tv_username,tv_pass,tv_email,tv_hello;

    nguoidung nguoidung = new nguoidung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        bottomNavigation = findViewById(R.id.bottom_navigation_infor);
        tv_email = findViewById(R.id.tv_email);
        tv_pass = findViewById(R.id.tv_password);
        tv_username = findViewById(R.id.tv_username);
        tv_hello = findViewById(R.id.tv_xinchao);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MY_FILE",MODE_PRIVATE);


        tv_pass.setText("Password: "+sharedPreferences.getString("password",""));
        tv_email.setText("Email: "+sharedPreferences.getString("email",""));
        tv_username.setText("Username: "+sharedPreferences.getString("email",""));


        tv_hello.setText("Chào mừng đến với Hello Toon");



        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notify));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_chart));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_logout));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),"Chức năng hiện chưa hoàn thiện",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Animatoo.animateSlideLeft(Information_Activity.this);
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),XepHang_Activity.class));
                        Animatoo.animateSlideLeft(Information_Activity.this);
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(Information_Activity.this);
                        break;
                }
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),"Chức năng hiện chưa hoàn thiện",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Animatoo.animateSlideLeft(Information_Activity.this);
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),XepHang_Activity.class));
                        Animatoo.animateSlideLeft(Information_Activity.this);
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(Information_Activity.this);
                        break;
                }

            }
        });

        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(2,true);

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        break;
                    case 5:

                        break;
                }
            }
        });

    }
}