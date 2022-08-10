package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.duan1_appdoctruyen.Model.nguoidung;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView dangky,khach,doimk;
    EditText edt_username,edt_password;
    Button btn_login,btn_cancel;
    nguoidung nguoidung = new nguoidung();

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


        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);


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
                JSONObject userA = new JSONObject();
                try {

                    userA.put("identifier",edt_username.getText().toString());
                    userA.put("password",edt_password.getText().toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.e("onClick: ", ""+userA);
                String url3 = "https://mysterious-wave-70860.herokuapp.com/api/auth/local";
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url3,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Animatoo.animateSlideRight(LoginActivity.this);
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("a",""+error);
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
                            Log.i("json", userA.toString());
                            return userA.toString().getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };

                queue.add(jsonObjectRequest1);
            };

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
                Bundle b = new Bundle();
                b.putSerializable("thongtin",nguoidung);

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("nd",b);
                Animatoo.animateZoom(LoginActivity.this);
                startActivity(intent);
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