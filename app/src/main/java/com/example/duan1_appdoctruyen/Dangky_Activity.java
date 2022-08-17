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
import com.example.duan1_appdoctruyen.Model.nguoidung;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Dangky_Activity extends AppCompatActivity {
    TextView dangnhap;
    EditText edt_dk_email,edt_dk_username,edt_dk_password,edt_dk_Repassword;
    Button btn_dk_dangky,btn_dk_cancel;

    nguoidung nguoidung = new nguoidung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        dangnhap = findViewById(R.id.tv_dk_dangnhap);
        edt_dk_email = findViewById(R.id.edt_dk_email);
        edt_dk_username = findViewById(R.id.edt_dk_username);
        edt_dk_password = findViewById(R.id.edt_dk_password);
        edt_dk_Repassword = findViewById(R.id.edt_dk_Repassword);
        btn_dk_dangky = findViewById(R.id.btn_dk_dangky);
        btn_dk_cancel = findViewById(R.id.btn_dk_cancel);

        nguoidung.setUsername(edt_dk_password.getText().toString());
        nguoidung.setEmail(edt_dk_email.getText().toString());
        nguoidung.setPassword(edt_dk_password.getText().toString());


        RequestQueue queue = Volley.newRequestQueue(Dangky_Activity.this);

        btn_dk_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //kiểu dữ liệu
                JSONObject userA = new JSONObject();
                Validate();

                try {
                    userA.put("username",edt_dk_username.getText().toString());
                    userA.put("email",edt_dk_email.getText().toString());
                    userA.put("password",edt_dk_password.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Log.e("onClick: ", ""+userA);
                String url3 = "https://mysterious-wave-70860.herokuapp.com/api/auth/local/register";
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, url3,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("Response", response.toString());
                        startActivity(new Intent(Dangky_Activity.this,LoginActivity.class));
                        Animatoo.animateSlideRight(Dangky_Activity.this);
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

    };
    public void Validate(){
        String email = edt_dk_email.getText().toString();
        String username = edt_dk_username.getText().toString();
        String password = edt_dk_password.getText().toString();
        String repassword = edt_dk_Repassword.getText().toString();
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui lòng không để trống thông tin",Toast.LENGTH_SHORT).show();
            return;
        } else if (edt_dk_password.getText().toString().length()<6){
            Toast.makeText(getApplicationContext(),"Mật khẩu phải nhiều hơn 7 ký tự",Toast.LENGTH_SHORT).show();
            return;
        }else if (!edt_dk_password.getText().toString().equals(edt_dk_Repassword.getText().toString())){
            Toast.makeText(getApplicationContext(),"Nhập lại mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Email không hợp lệ vui lòng nhập lại",Toast.LENGTH_SHORT).show();
            return;
        }


    }

}
