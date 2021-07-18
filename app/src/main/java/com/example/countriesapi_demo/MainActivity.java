package com.example.countriesapi_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://restcountries.eu/rest/v2";
    ListView listView;
    ArrayList<itemModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        sendAndRequestResponse();
    }
    private void sendAndRequestResponse() {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0; i < array.length() ; i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        itemModel model = new itemModel();
                        model.setCapital(jsonObject.getString("capital"));
                        model.setCountryName(jsonObject.getString("name"));
                        arrayList.add(model);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
    }
}