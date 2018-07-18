package com.example.miestro.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String Json_Url ="http://grapesnberries.getsandbox.com/products?count=10&from=1";
    TextView textView;
    private static final String TAG = "MainActivity";
    private static final int Num_COl = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // textView = (TextView)findViewById(R.id.textview);
        getlist();

    }

    public void getlist(){


        final ArrayList<Contents> content_list=new ArrayList<Contents>();




        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,Json_Url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                          //  textView.setText(response+"");
                            for(int i=0;i<response.length();i++){

                                JSONObject respons = response.getJSONObject(i);
                                String id=respons.getString("id");
                                String productDescription=respons.getString("productDescription");
                                String price=respons.getString("price");
                                JSONObject image = respons.getJSONObject("image");
                                String url = image.getString("url");
                                String height  = image.getString("height");

                                content_list.add(new Contents( productDescription, price,url,height));

                            }

                            if(content_list.size()>0) {
                              /*  adapter = new RecyclerAdapter(content_list, Display_List.this);
                                recyclerView.setAdapter(adapter);*/
                              dispaly(content_list);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }},new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","error");
                Toast.makeText(MainActivity.this,"Volley error",Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(jsonArrayRequest);
        //  return content_list;


    }

    public void dispaly( ArrayList<Contents> content_list){

    /*    for (int a=0;a<content_list.size();a++){
            textView.append(content_list.get(a).getProduct_Description()+" "+content_list.get(a).getPrice()+" "+content_list.get(a).getImage()+"\n");
        }
*/
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(this,content_list);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(Num_COl, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }


}
