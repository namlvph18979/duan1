package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class DoiMatKhauActivity extends AppCompatActivity {
    TextView dangnhap;

    EditText edt_email,edt_mkc,edt_mkm,edt_remkm;
    Button btn_luu,btn_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edt_email = findViewById(R.id.edt_dmk_username);
        edt_mkc = findViewById(R.id.edt_dmk_oldpass);
        edt_mkm = findViewById(R.id.edt_dmk_newpassword);
        edt_remkm = findViewById(R.id.edt_dmk_Repassword);

        btn_luu = findViewById(R.id.btn_dmk_doi);
        btn_luu = findViewById(R.id.btn_dmk_cancel);

        RequestQueue queue = Volley.newRequestQueue(DoiMatKhauActivity.this);



        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validate();

                //đẩy dữ liệu
                JSONObject userA = new JSONObject();
                Validate();

                try {
                    userA.put("currentPassword",edt_mkc.getText().toString());
                    userA.put("password",edt_mkm.getText().toString());
                    userA.put("passwordConfirmation",edt_remkm.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.e("onClick: ", ""+userA);
                String url3 = "https://mysterious-wave-70860.herokuapp.com/api/auth/change-password";
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.PUT, url3,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                        startActivity(new Intent(DoiMatKhauActivity.this,LoginActivity.class));
                        Animatoo.animateSlideRight(DoiMatKhauActivity.this);
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
                        params.put("x-vacationtoken", "secret_token");
                        params.put("content-type", "application/json");
                        params.put("Authorization", "Bearer <user jwt token>");

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

            }
        });


        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_email.setText("");
                edt_mkc.setText("");
                edt_mkm.setText("");
                edt_remkm.setText("");
            }
        });
        dangnhap = findViewById(R.id.tv_dmk_dangnhap);

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoiMatKhauActivity.this,LoginActivity.class));
                Animatoo.animateSlideLeft(DoiMatKhauActivity.this);
            }
        });
    }

    public void Validate(){
        String email = edt_email.getText().toString();
        String passold = edt_mkc.getText().toString();
        String passwordnew = edt_mkm.getText().toString();
        String repassword = edt_remkm.getText().toString();
        if (email.isEmpty() || passold.isEmpty() || passwordnew.isEmpty() || repassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui lòng không để trống thông tin",Toast.LENGTH_SHORT).show();
            return;
        } else if (edt_mkm.getText().toString().length()<6){
            Toast.makeText(getApplicationContext(),"Mật khẩu phải nhiều hơn 7 ký tự",Toast.LENGTH_SHORT).show();
            return;
        }else if (!edt_mkm.getText().toString().equals(edt_remkm.getText().toString())){
            Toast.makeText(getApplicationContext(),"Nhập lại mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Email không hợp lệ vui lòng nhập lại",Toast.LENGTH_SHORT).show();
            return;
        }


    }
}