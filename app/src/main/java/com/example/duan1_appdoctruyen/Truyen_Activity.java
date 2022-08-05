package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.duan1_appdoctruyen.Adapter.Truyentranh_Adapter;
import com.example.duan1_appdoctruyen.Adapter.chap_Adapter;
import com.example.duan1_appdoctruyen.Model.Chap;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Truyen_Activity extends AppCompatActivity {

    ImageView img_back,img_heart;
    ListView listView;
    TextView tvname,tvtheloai,tv_like,tv_view;
    Chap chap = new Chap();
    chap_Adapter adapter;
    ArrayList<Chap> list = new ArrayList<>();
    int dem,demview;
    int dem2 = 0;

    TruyenTranh truyenTranh = new TruyenTranh();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        tvname = findViewById(R.id.tv_namecomic);
        tvtheloai = findViewById(R.id.the_loai2);
        tv_view = findViewById(R.id.tv_view);
        tv_like = findViewById(R.id.tv_like);

        img_back = findViewById(R.id.img_back);
        img_heart = findViewById(R.id.img_like);
        listView = findViewById(R.id.lv_listchaptruyen);

        // lay ten truyen
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh =(TruyenTranh) b.getSerializable("truyen");

        tvname.setText(truyenTranh.getTenTruyen());
        tvtheloai.setText(truyenTranh.getTheloai());
        tv_view.setText("View: "+truyenTranh.getLuotview());
        demview = Integer.parseInt(truyenTranh.getLuotview());

        tv_view.setText("View: "+ ++demview);
        tv_like.setText("Like: "+truyenTranh.getLuotthich());
        dem = Integer.parseInt(truyenTranh.getLuotthich());




        RequestQueue queue = Volley.newRequestQueue(this);

        // link api
        final String url = "https://mysterious-wave-70860.herokuapp.com/api/chap-truyens?populate=*";


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

                        list.add(new Chap("Chap: "+i,"10/8/2021",jsonArray.getString("noi_dung")));

                        }
                    adapter = new chap_Adapter(getApplicationContext(),list);
                    listView.setAdapter(adapter);

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



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chap chap = list.get(position);
                Bundle b = new Bundle();
                b.putSerializable("noidung",chap);

                Intent intent = new Intent(getApplicationContext(),NoiDung_Activity.class);
                intent.putExtra("nd",b);
                startActivity(intent);
            }
        });

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
                dem2++;

                    if (dem2%2!=0){
                        img_heart.setImageResource(R.drawable.heartnew2);
                        tv_like.setText("like: "+ ++dem);
                        Toast.makeText(getApplicationContext(),"Đã thích truyện",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        img_heart.setImageResource(R.drawable.heartnew1);
                        tv_like.setText("like: "+ --dem);
                        Toast.makeText(getApplicationContext(),"Đã bỏ thích truyện",Toast.LENGTH_SHORT).show();
                        return;
                }

            }
        });


    }


}