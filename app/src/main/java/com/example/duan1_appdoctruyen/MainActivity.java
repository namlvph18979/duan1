package com.example.duan1_appdoctruyen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1_appdoctruyen.Adapter.Slider_Adapter;
import com.example.duan1_appdoctruyen.Adapter.Truyentranh_Adapter;
import com.example.duan1_appdoctruyen.Adapter.listxoa_Adapter;
import com.example.duan1_appdoctruyen.Animation.TranslateAnimation;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;
import com.example.duan1_appdoctruyen.Model.truyenDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    SliderView sliderView;
    int[] image_slide = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};
    MeowBottomNavigation bottomNavigation;
    ScrollView scrollView;
    GridView gridView;
    EditText edt_timkiem;


    FloatingActionMenu flbmenu;
    FloatingActionButton flb1,flb2,flb3;

    Truyentranh_Adapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scroll_view);
        edt_timkiem = findViewById(R.id.edt_timkiem);
        flbmenu = findViewById(R.id.flb_menu);
        flb1 = findViewById(R.id.flb1);
        flb2 = findViewById(R.id.flb2);
        flb3 = findViewById(R.id.flb3);




        // fake data

        gridView = findViewById(R.id.grid_view);
        truyenTranhArrayList = new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));
        truyenTranhArrayList.add(new TruyenTranh("Nghịch thiên tà thần","Chap 889",R.drawable.vidu));


        // gán dữ liệu cho gridview và xử lý slidershow

        adapter = new Truyentranh_Adapter(this,0,truyenTranhArrayList);
        gridView.setAdapter(adapter);

        sliderView = findViewById(R.id.slide_view);
        Slider_Adapter slider_adapter = new Slider_Adapter(image_slide);
        sliderView.setSliderAdapter(slider_adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        // Gán icon và xử lý click bottomnavigation

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notify));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_add));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_logout));




        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),"ban chon case1",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),Information_Activity.class));
                        Animatoo.animateSlideRight(MainActivity.this);
                        break;
                    case 3:

                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"ban chon case4",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(MainActivity.this);
                        break;
                }

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),"ban chon case1",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),Information_Activity.class));
                        Animatoo.animateSlideRight(MainActivity.this);
                        break;
                    case 3:

                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),"ban chon case4",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(MainActivity.this);
                        break;
                }

            }
        });

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


        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(3,true);


//         Xử lý onclick trên item gridview

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),Truyen_Activity.class));
            }
        });


        // Xử lý sự kiện show and hide bottomnavigation + FloatingActionButton

        scrollView.setOnTouchListener(new TranslateAnimation(this,bottomNavigation));
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0){
                    flbmenu.setVisibility(View.GONE);
                }else{
                    flbmenu.setVisibility(View.VISIBLE);
                }
            }
        });


        // Xự kiện Floatting Action Button
        flb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Help_Activity.class));
            }
        });

        flb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        flb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Delete_Activity.class));
            }
        });


    }

}