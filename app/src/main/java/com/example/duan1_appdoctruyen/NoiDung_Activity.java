package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan1_appdoctruyen.Model.Chap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoiDung_Activity extends AppCompatActivity {

    ImageView img_back;
    TextView noidung;
    Button btn_back,btn_continue;
    ArrayList<String> arrayList = new ArrayList<>();
    Chap chap = new Chap();
    int j = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);

        noidung = findViewById(R.id.tv_noidung);
        btn_continue = findViewById(R.id.btn_chapsau);
        btn_back = findViewById(R.id.btn_chaptruoc);
        img_back = findViewById(R.id.img_back);



        Bundle b = getIntent().getBundleExtra("nd");
        chap =(Chap) b.getSerializable("noidung");


        noidung.setText(chap.getNoidung());


        RequestQueue queue = Volley.newRequestQueue(this);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

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
                        String noi_dung = jsonArray.getString("noi_dung");
                        arrayList.add(noi_dung);

                    }
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

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noidung.setText(arrayList.get(++j));


            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (j<=0){
                        return;
                    }else {
                    noidung.setText(arrayList.get(--j));
                }
            }
        });


    }
}