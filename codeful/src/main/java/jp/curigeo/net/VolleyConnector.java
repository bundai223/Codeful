package jp.curigeo.net;


import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * ref) http://vividcode.hatenablog.com/entry/android-app/volley-basis
 * Created by nishimuradaiji on 2014/07/14.
 */
public class VolleyConnector implements Connector {

    static private RequestQueue sQueue;

    public VolleyConnector(Context context) {
        if (sQueue == null) {
            sQueue = Volley.newRequestQueue(context);
        }
    }

    @Override
    public void connect(String url, ConnectCallback callback) {

    }
}
