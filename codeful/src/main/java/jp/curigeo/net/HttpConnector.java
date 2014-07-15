package jp.curigeo.net;

import android.os.AsyncTask;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import jp.curigeo.util.Logger;

import java.io.IOException;

/**
 * Created by nishimuradaiji on 2014/07/14.
 */
public class HttpConnector implements Connector {
    private OkHttpClient httpClient = null;

    public HttpConnector() {
        httpClient = new OkHttpClient();
    }

    /**
     * 接続
     * @param url
     */
    public void connect(final String url) {
        connect(url, null);
    }

    /**
     * Http通信（完了コールバックつき）
     * @param url
     * @param callback
     */
    public void connect(final String url, final Connector.Callback callback) {
        AsyncTask<String, Integer, Integer> task = new AsyncTask<String, Integer, Integer>() {
            protected Integer doInBackground(String... params) {
                String keyword = params[0];

                // TODO: Header
                Request request = new Request.Builder().url(url).addHeader("User-Agent", "test").build();

                try {
                    Response response = httpClient.newCall(request).execute();
                    final ResponseBody body = response.body();

                    String rawResponse = body.string();
                    Logger.i(rawResponse);

                    // TODO: Error
                    if (callback != null) {
                        callback.onComplete(rawResponse);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    return 0;
                }
            }
        };

        String[] array = { url };
        task.execute(array);
    }

}
