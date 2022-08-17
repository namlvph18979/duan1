package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan1_appdoctruyen.Model.TruyenTranh;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Them_Activity extends AppCompatActivity {

    EditText tentruyen,chaptruyen,theloai,linkanh;
    Button btn_them,btn_huy;
    ImageView back;

    TruyenTranh truyenTranh = new TruyenTranh();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        tentruyen = findViewById(R.id.edt_tentruyen);
        chaptruyen = findViewById(R.id.edt_Chap);
        theloai = findViewById(R.id.edt_theloai);
        linkanh = findViewById(R.id.edt_linkanh);
        back = findViewById(R.id.img_back);


        btn_them = findViewById(R.id.btn_them);
        btn_huy = findViewById(R.id.btn_huy);


        RequestQueue queue = Volley.newRequestQueue(this);



        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2 = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens?populate=*";

                JSONObject itemA = new JSONObject();
                Validate();
                try {
                    itemA.put("tieu_de_truyen", tentruyen.getText());
                    itemA.put("so_chuong",chaptruyen.getText());
                    itemA.put("ten_the_loai",theloai.getText());
                    itemA.put("luot_view","0");
                    itemA.put("luot_thich","0");
                    itemA.put("trang_thai","Đang ra");
                    itemA.put("url",linkanh1(String.valueOf(linkanh.getText())));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("data", itemA);

                    Toast.makeText(getApplicationContext(),"Them thanh cong",Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    // handle exception
                }
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_SHORT).show();
                    }


                }){    //this is the part, that adds the header to the request
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("x-vacationtoken", "secret_token");
                        params.put("content-type", "application/json");
                        return params;
                    }
                    @Override
                    public byte[] getBody() {

                        try {
                            Log.i("json", jsonObject.toString());
                            return jsonObject.toString().getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };

                queue.add(jsonObjectRequest1);




            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentruyen.setText("");
                chaptruyen.setText("");
                linkanh.setText("");
                theloai.setText("");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Delete_Activity.class));
            }
        });
    }

    public void Validate(){
        if (tentruyen.getText().toString().isEmpty() || chaptruyen.getText().toString().isEmpty()
                || linkanh.getText().toString().isEmpty()
                || theloai.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui long nhap du thong tin",Toast.LENGTH_SHORT).show();
        }
        return;
    }

    public String linkanh1(String link){

        String url2 = "https://mysterious-wave-70860.herokuapp.com/api/upload/";

        JSONObject itemB = new JSONObject();
        Validate();
        try {
            itemB.put("url",link);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("img_truyen", itemB);


        } catch (JSONException e) {
            // handle exception
        }
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("Response", response.toString());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }


        }){    //this is the part, that adds the header to the request
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("x-vacationtoken", "secret_token");
                params.put("content-type", "application/json");
                return params;
            }
            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest2);

        return "";
    }

}