package jp.curigeo.net;

import android.content.Context;
import android.util.LruCache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by daiji on 2014/07/15.
 */
public class VolleyUtil {
    static boolean initialized = false;
    static private RequestQueue queue;
    static private ImageLoader imageLoader;

    static public void initialize(Context context) {
        if (initialized == false) {
            queue = Volley.newRequestQueue(context);
            imageLoader = new ImageLoader(queue, new LruImageCache());

            initialized = true;
        }
    }

//    static public void onPause() {
//        if (queue != null) {
//            queue.stop();
//        }
//    }
//
//    static public void onResume() {
//        if (queue != null) {
//            queue.start();
//        }
//    }

    static public ImageLoader getImageLoader() {
        return imageLoader;
    }

    static public RequestQueue getQueue() {
        return queue;
    }
}
