package com.example.duan1_appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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
    LinearLayout them;
    EditText findDelete;
    ListView lis1;
    listxoa_Adapter adapter;
    ArrayList<TruyenTranh> list2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete);


        them = findViewById(R.id.them);
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
                        JSONObject jsonObject2 = jsonArray.getJSONObject("img_truyen");
                        JSONObject jsonObject6 = jsonObject2.getJSONObject("data");
                        JSONObject jsonObject7 = jsonObject6.getJSONObject("attributes");
                        JSONObject jsonObject8 = jsonObject7.getJSONObject("formats");
                        JSONObject jsonObject9 = jsonObject8.getJSONObject("thumbnail");
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject jsonObject3 = jsonArray2.getJSONObject(j);
                            JSONObject jsonObject4 = jsonObject3.getJSONObject("attributes");

                            list2.add(new TruyenTranh(jsonArray.getString("tieu_de_truyen"), jsonArray.getString("so_chuong")
                                    , jsonObject4.getString("ten_the_loai"), jsonArray.getString("luot_view"), jsonArray.getString("luot_thich"),String.valueOf(jsonObject.getInt("id")),jsonObject9.getString("url")));

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



        lis1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                RequestQueue queue = Volley.newRequestQueue(Delete_Activity.this);
                final String url = "https://mysterious-wave-70860.herokuapp.com/api/ten-truyens/"+list2.get(position).getId();
                AlertDialog.Builder builder =new AlertDialog.Builder(Delete_Activity.this);
                builder.setTitle("DELETE");
                builder.setIcon(R.drawable.ic_delete);
                builder.setMessage("Bạn muốn xóa không?");
                builder.setCancelable(true);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TruyenTranh truyenTranh = list2.get(position);

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    Toast.makeText(Delete_Activity.this,"Xoa thanh cong",Toast.LENGTH_SHORT).show();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }}, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }


                        });



                        lis1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        queue.add(jsonObjectRequest);
                        dialog.cancel();

                    }
                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
//                AlertDialog alertDialog = builder.create();
                builder.show();

                return true;
            }
        });

        lis1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TruyenTranh truyenTranh = list2.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyenTranh);
                Intent intent = new Intent(getApplicationContext(),Sua_Activity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });

                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Them_Activity.class));
                    }
                });

                findDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String find = findDelete.getText().toString();
                        ArrayList<TruyenTranh> arrayList = new ArrayList<>();
                        for (int i = 0; i < list2.size(); i++) {
                            if (list2.get(i).getTenTruyen().toLowerCase().contains(find.toLowerCase())){
                                arrayList.add(new TruyenTranh(list2.get(i).getTenTruyen(),list2.get(i).getTenChap(),
                                        list2.get(i).getTheloai(),list2.get(i).getLuotview(),list2.get(i).getLuotthich(),list2.get(i).getId(),
                                        list2.get(i).getImg()));
                                adapter = new listxoa_Adapter(getApplicationContext(),arrayList);
                                lis1.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else if (find.isEmpty()){
                                adapter = new listxoa_Adapter(getApplicationContext(),list2);
                                lis1.setAdapter(adapter);

                            }

                        }

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