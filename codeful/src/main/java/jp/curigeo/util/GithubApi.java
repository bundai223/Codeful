package jp.curigeo.util;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishimuradaiji on 2014/07/09.
 */
public class GithubApi {

    private final String API_URL_BASE = "https://api.github.com/";

    private OkHttpClient httpClient = null;
    private Map<Integer, Request> requestMap = null;

    public GithubApi() {
        httpClient = new OkHttpClient();
        requestMap = new HashMap<Integer, Request>();
    }

    public void requestSearchUser(String keyword) {
        String apiurl = String.format("https://api.github.com/search/users?q=%s", keyword);

    }

    public void requestSearchRepository(String keyword) {
        String apiurl = String.format("https://api.github.com/search/repositories?q=%s", keyword);
    }

    public void requestlistupUserRepository(String username) {
        String apiurl = String.format("https://api.github.com/users/%s/repos", username);

    }

    public void requestGetUserRepository(String username, String repositoryName) {
        String apiurl = String.format("https://api.github.com/repos/%s/%s/zipall", username, repositoryName);

    }
}
