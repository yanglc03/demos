package com.example.testApp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testApp.NetWorkUtil.NetworkTool;
import org.json.JSONArray;

public class MyActivity extends Activity {
    NetworkImageView networkImageView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getImage();
        getJSONArray();
    }

    private void getImage() {
        networkImageView.setErrorImageResId(R.drawable.ic_launcher);
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        networkImageView.setImageUrl("http://res.tvxio.com/media/upload/20140922/12170900xiaoniuvsnikesi001.jpg",
                NetworkTool.getInstance(this).getImageLoader());
    }

    private void getJSONArray() {
        String url = "http://cord.tvxio.com/api/tv/sections/teleplay/";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("getJSONArray", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("getJSONArray", error.toString());
            }
        });
        NetworkTool.getInstance(this).getRuquestQueue().add(jsonArrayRequest);
    }
}
