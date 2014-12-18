package com.example.testApp.NetWorkUtil;

import android.content.Context;
import android.util.Log;
//import com.android.diskLruCache.ImageLruCache;
import com.android.diskLruCache.ImageLruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by yanglc1 on 14/12/17.
 */
public class NetworkTool {
    private static NetworkTool networkTool;
    private static final Object lock = new Object();
    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;
    private static ImageLruCache imageLruCache;

    private NetworkTool(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLruCache = new ImageLruCache();
        imageLoader = new ImageLoader(requestQueue, imageLruCache);
    }

    public static synchronized NetworkTool getInstance(Context context) {
        Log.d("NetworkTool", "getInstance");
        if (networkTool == null) {
            Log.d("NetworkTool", "new NetworkTool");
            synchronized (lock) {
                if (networkTool == null)
                    networkTool = new NetworkTool(context);
            }
        }
        return networkTool;
    }

    public RequestQueue getRuquestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
