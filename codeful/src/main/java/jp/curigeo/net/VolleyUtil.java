package jp.curigeo.net;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by daiji on 2014/07/15.
 */
public class VolleyUtil {
    static private RequestQueue queue;

    static public void initialize(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }
    }

    static public RequestQueue getQueue() {
        return queue;
    }
}
