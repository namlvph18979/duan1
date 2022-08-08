package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan1_appdoctruyen.Adapter.Truyentranh_Adapter;
import com.example.duan1_appdoctruyen.Adapter.listxoa_Adapter;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;
import com.example.duan1_appdoctruyen.Model.truyenDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Delete_Activity extends AppCompatActivity {


    ImageView img_remove;
    EditText findDelete;
    ListView lis1;
    listxoa_Adapter adapter;
    ArrayList<TruyenTranh> list2 = new ArrayList<>();
    TruyenTranh truyenTranh = new TruyenTranh();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete);


        img_remove = findViewById(R.id.img_remove);
        findDelete = findViewById(R.id.edt_timtruyen_xoa);
        lis1 = findViewById(R.id.lv_xoatruyen);
        
        RequestQueue queue = Volley.newRequestQueue(Delete_Activity.this);
        final String url = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens?populate=*";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //nhan json array "data" tra ve
                    JSONArray responseJSONArray = response.getJSONArray("data");
                    //vong lap cho tung array o trong du lieu tra lai

                    for (int i = 0; i < responseJSONArray.length(); i++) {
                        //get json object trong tung array
                        JSONObject jsonObject = responseJSONArray.getJSONObject(i);
                        //lay du lieu mang "attributes"
                        JSONObject jsonArray = jsonObject.getJSONObject("attributes");
                        JSONObject jsonObject1 = jsonArray.getJSONObject("the_loai");
                        JSONArray jsonArray2 = jsonObject1.getJSONArray("data");
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
                            JSONObject jsonObject4 = jsonObject3.getJSONObject("attributes");

                            list2.add(new TruyenTranh(jsonArray.getString("tieu_de_truyen"), jsonArray.getString("so_chuong")
                                    , jsonObject4.getString("ten_the_loai"), jsonArray.getString("luot_view"), jsonArray.getString("luot_thich"),String.valueOf(jsonArray.getInt("id"))));

                        }

                    }

                    adapter = new listxoa_Adapter(getApplicationContext(),list2);
                    lis1.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }}, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
        queue.add(jsonObjectRequest);


                findDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                img_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });

            }



}