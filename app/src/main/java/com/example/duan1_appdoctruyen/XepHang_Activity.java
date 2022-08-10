package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.duan1_appdoctruyen.Adapter.Truyentranh_Adapter;
import com.example.duan1_appdoctruyen.Adapter.XepHang_Adapter;
import com.example.duan1_appdoctruyen.Adapter.listxoa_Adapter;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class XepHang_Activity extends AppCompatActivity {


    MeowBottomNavigation bottomNavigation;
    ListView listtop;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    XepHang_Adapter adapter;
    TruyenTranh truyenTranh = new TruyenTranh();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xep_hang);

        listtop = findViewById(R.id.lv_list);
        bottomNavigation = findViewById(R.id.bottom_navigation_xephang);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_notify));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_chart));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_logout));

        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");


        RequestQueue queue = Volley.newRequestQueue(XepHang_Activity.this);
        truyenTranhArrayList = new ArrayList<>();

        String url = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens/?sort[0]=luot_view%3Adesc";
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


                            truyenTranhArrayList.add(new TruyenTranh(jsonArray.getString("tieu_de_truyen"),jsonArray.getString("so_chuong")
                                    ,"tl1",jsonArray.getString("luot_view"),jsonArray.getString("luot_thich"),String.valueOf(jsonObject.getInt("id")),truyenTranh.getImg()));


                    }

                    adapter = new XepHang_Adapter(XepHang_Activity.this,0,truyenTranhArrayList);
                    listtop.setAdapter(adapter);

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








        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        Toast.makeText(getApplicationContext(),"Chức năng hiện chưa hoàn thiện",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),Information_Activity.class));
                        Animatoo.animateSlideRight(XepHang_Activity.this);
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Animatoo.animateSlideRight(XepHang_Activity.this);
                        break;
                    case 4:

                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(XepHang_Activity.this);
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
                        startActivity(new Intent(getApplicationContext(),Information_Activity.class));
                        Animatoo.animateSlideRight(XepHang_Activity.this);
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Animatoo.animateSlideRight(XepHang_Activity.this);
                        break;
                    case 4:

                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        Animatoo.animateShrink(XepHang_Activity.this);
                        break;
                }

            }
        });

        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(4,true);

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