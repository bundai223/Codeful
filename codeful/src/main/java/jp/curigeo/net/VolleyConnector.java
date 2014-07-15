package jp.curigeo.net;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import jp.curigeo.util.Logger;

/**
 * ref) http://vividcode.hatenablog.com/entry/android-app/volley-basis
 * Created by nishimuradaiji on 2014/07/14.
 */
public class VolleyConnector implements Connector {

    public VolleyConnector() {
    }

    @Override
    public void connect(String url, final Connector.Callback callback) {
        final Request<String> request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.d(response.toString());
                        if (callback != null) {
                            callback.onComplete(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (callback != null) {
                            callback.onCompleteWithError();
                        }
                    }
                });

        VolleyUtil.getQueue().add(request);
    }
}
