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

public class Sua_Activity extends AppCompatActivity {

    EditText ten,chap,anh,theloai;
    Button save,cancel;
    TruyenTranh truyenTranh = new TruyenTranh();
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);

        ten = findViewById(R.id.edt_tentruyen);
        chap = findViewById(R.id.edt_Chap);
        anh = findViewById(R.id.edt_linkanh);
        save = findViewById(R.id.btn_luu);
        cancel = findViewById(R.id.btn_huy);
        theloai = findViewById(R.id.edt_theloai);
        back = findViewById(R.id.img_back);

        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");

        ten.setText(truyenTranh.getTenTruyen());
        chap.setText(truyenTranh.getTenChap());
        theloai.setText(truyenTranh.getTheloai());
        anh.setText(truyenTranh.getImg());

        RequestQueue queue = Volley.newRequestQueue(this);

        String url2 = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens/"+truyenTranh.getId()+"?populate=*";

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject itemA = new JSONObject();
                try {
                    itemA.put("tieu_de_truyen", ten.getText());
                    itemA.put("so_chuong",chap.getText());
                    itemA.put("url",anh.getText());
                    itemA.put("ten_the_loai",theloai.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("data", itemA);

                    Toast.makeText(getApplicationContext(),"Luu thanh cong",Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    // handle exception
                }
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.PUT, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Luu that bai",Toast.LENGTH_SHORT).show();
                    }


                }){    //this is the part, that adds the header to the request
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        //params.put("x-vacationtoken", "secret_token");
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
                requestQueue.add(jsonObjectRequest1);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten.setText("");
                chap.setText("");
                anh.setText("");
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
}