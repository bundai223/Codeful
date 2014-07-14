package jp.curigeo.net.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Request;
import jp.curigeo.net.ConnectCallback;
import jp.curigeo.net.Connector;
import jp.curigeo.net.VolleyConnector;
import jp.curigeo.util.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishimuradaiji on 2014/07/09.
 */
public class GithubApi {

    private final String API_URL_BASE = "https://api.github.com/";

    public GithubApi() {
    }

    public void requestSearchUser(String keyword) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/search/users?q=%s", encodeUrl(keyword));

        connect(apiurl, new ConnectCallback() {
            @Override
            public void onComplete(String response) {
                Gson gson = new Gson();
                ResponseUserInfo responseInfo = gson.fromJson(response, ResponseUserInfo.class);
                Logger.d(responseInfo.toString());
            }

            @Override
            public void onCompleteWithError() {

            }
        });
   }

    public void requestSearchRepository(String keyword) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/search/repositories?q=%s", encodeUrl(keyword));

        connect(apiurl, null);
    }

    public void requestlistupUserRepository(String username) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/users/%s/repos", encodeUrl(username));

        connect(apiurl, null);
    }

    public void requestGetUserRepository(String username, String repositoryName) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/repos/%s/%s/zipall", encodeUrl(username), encodeUrl(repositoryName));

        connect(apiurl, null);
    }

    private void connect(String url, ConnectCallback callback) {
        //Connector connector = new HttpConnector();
        Connector connector = new VolleyConnector();
        connector.connect(url, callback);
    }

    private String encodeUrl(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }
}
