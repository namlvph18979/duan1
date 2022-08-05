package com.example.duan1_appdoctruyen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1_appdoctruyen.Adapter.Slider_Adapter;
import com.example.duan1_appdoctruyen.Adapter.Truyentranh_Adapter;
import com.example.duan1_appdoctruyen.Adapter.listxoa_Adapter;
import com.example.duan1_appdoctruyen.Animation.TranslateAnimation;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;
import com.example.duan1_appdoctruyen.Model.nguoidung;
import com.example.duan1_appdoctruyen.Model.truyenDialog;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    SliderView sliderView;
    int[] image_slide = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};
    MeowBottomNavigation bottomNavigation;
    ScrollView scrollView;
    GridView gridView;
    LinearLayout hanh_dong,kinh_di,trinh_tham,kiem_hiep,ngon_tinh,co_dai,xuyen_khong,dam_my;
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

        hanh_dong = findViewById(R.id.hanh_dong);
        kinh_di = findViewById(R.id.kinh_di);
        trinh_tham = findViewById(R.id.trinh_tham);
        kiem_hiep = findViewById(R.id.kiem_hiep);
        ngon_tinh = findViewById(R.id.ngon_tinh);
        co_dai = findViewById(R.id.co_dai);
        xuyen_khong = findViewById(R.id.xuyen_khong);
        dam_my = findViewById(R.id.dam_my);

        scrollView = findViewById(R.id.scroll_view);
        edt_timkiem = findViewById(R.id.edt_timkiem);
        flbmenu = findViewById(R.id.flb_menu);
        flb1 = findViewById(R.id.flb1);
        flb2 = findViewById(R.id.flb2);
        flb3 = findViewById(R.id.flb3);

        TruyenTranh truyenTranh = new TruyenTranh();




        // fake data

        gridView = findViewById(R.id.grid_view);
        truyenTranhArrayList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // link api
        final String url = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens?populate=*";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //nhan json array "data" tra ve
                    JSONArray responseJSONArray = response.getJSONArray("data");
                    //vong lap cho tung array o trong du lieu tra lai

                    for (int i = 0;i < responseJSONArray.length();i++) {
                        //get json object trong tung array
                        JSONObject jsonObject = responseJSONArray.getJSONObject(i);
                        //lay du lieu mang "attributes"
                        JSONObject jsonArray = jsonObject.getJSONObject("attributes");
                        JSONObject jsonObject1 = jsonArray.getJSONObject("the_loai");
                        JSONArray jsonArray2 = jsonObject1.getJSONArray("data");
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
                            JSONObject jsonObject4 = jsonObject3.getJSONObject("attributes");

                            truyenTranhArrayList.add(new TruyenTranh(jsonArray.getString("tieu_de_truyen"),jsonArray.getString("so_chuong")
                                    ,jsonObject4.getString("ten_the_loai"),jsonArray.getString("luot_view"),jsonArray.getString("luot_thich")));

                        }

                    }

                    adapter = new Truyentranh_Adapter(MainActivity.this,0,truyenTranhArrayList);
                    gridView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);


// Tim theo the loai
        hanh_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               theloai_find("Hành Động");
            }
        });

        kinh_di.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Kinh dị");
            }
        });

        trinh_tham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Trinh thám");
            }
        });

        kiem_hiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Kiếm hiệp");
            }
        });

        ngon_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Ngôn tình");
            }
        });

        co_dai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Cổ đại");
            }
        });

        xuyen_khong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Xuyên không");
            }
        });

        dam_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theloai_find("Đam mỹ");
            }
        });


// gán dữ liệu cho gridview và xử lý slidershow
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
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_chart));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_logout));


        //the loai onclick



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

                    TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                    Bundle b = new Bundle();
                    b.putSerializable("truyen",truyenTranh);
                    Intent intent = new Intent(getApplicationContext(),Truyen_Activity.class);
                    intent.putExtra("data",b);
                    startActivity(intent);
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

    public void theloai_find(String theloai){
        ArrayList<TruyenTranh> list = new ArrayList<>();
        for (int i = 0; i < truyenTranhArrayList.size(); i++) {
            if (truyenTranhArrayList.get(i).getTheloai().equals(theloai)){

                list.add(new TruyenTranh(truyenTranhArrayList.get(i).getTenTruyen(),
                        truyenTranhArrayList.get(i).getTenChap(),truyenTranhArrayList.get(i).getTheloai(),
                        truyenTranhArrayList.get(i).getLuotview(),truyenTranhArrayList.get(i).getLuotthich()));
            }
            adapter = new Truyentranh_Adapter(MainActivity.this,0,list);
            gridView.setAdapter(adapter);
            gridView.deferNotifyDataSetChanged();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TruyenTranh truyenTranh = list.get(position);
                    Bundle b = new Bundle();
                    b.putSerializable("truyen",truyenTranh);
                    Intent intent = new Intent(getApplicationContext(),Truyen_Activity.class);
                    intent.putExtra("data",b);
                    startActivity(intent);
                }

            });

        }
    }

}