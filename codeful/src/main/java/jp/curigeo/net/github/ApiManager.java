package jp.curigeo.net.github;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jp.curigeo.net.ApiCallback;
import jp.curigeo.net.Connector;
import jp.curigeo.net.VolleyConnector;
import jp.curigeo.util.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by nishimuradaiji on 2014/07/09.
 */
public class ApiManager {
    public interface Callback<T> {
        void onComplete(T result);
        void onError();
    }

    private final String API_URL_BASE = "https://api.github.com/";

    public ApiManager() {
    }

    public void requestSearchUser(String keyword, final Callback<ResponseSearchUser> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/search/users?q=%s", encodeUrl(keyword));

        connect(apiurl, new Connector.Callback() {
            @Override
            public void onComplete(String response) {
                Gson gson = new Gson();
                ResponseSearchUser responseUser = gson.fromJson(response, ResponseSearchUser.class);
                Logger.d(responseUser.toString());

                callback.onComplete(responseUser);
            }

            @Override
            public void onCompleteWithError() {

            }
        });
   }

    public void requestSearchRepository(String keyword, final Callback<ResponseSearchRepository> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/search/repositories?q=%s", encodeUrl(keyword));

        connect(apiurl, new Connector.Callback() {
            @Override
            public void onComplete(String response) {
                Gson gson = new Gson();
                ResponseSearchRepository responseRepository = gson.fromJson(response, ResponseSearchRepository.class);
                Logger.d(responseRepository.toString());

                callback.onComplete(responseRepository);
            }

            @Override
            public void onCompleteWithError() {
                callback.onError();
            }
        });
    }

    public void requestlistupUserRepository(String username, final Callback<ResponseSearchRepository> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("https://api.github.com/users/%s/repos", encodeUrl(username));

        connect(apiurl, new Connector.Callback() {
            @Override
            public void onComplete(String response) {
                Gson gson = new Gson();
                TypeToken<List<RepositoryInfo>> token = new TypeToken<List<RepositoryInfo>>(){};
                List<RepositoryInfo> repositoryList = gson.fromJson(response, token.getType());
                ResponseSearchRepository responseRepository = new ResponseSearchRepository(repositoryList);
                Logger.d(responseRepository.toString());

                callback.onComplete(responseRepository);
            }

            @Override
            public void onCompleteWithError() {
                callback.onError();
            }
        });
    }

//    public void requestGetUserRepository(String username, String repositoryName) throws UnsupportedEncodingException {
//        String apiurl = String.format("https://api.github.com/repos/%s/%s/zipall", encodeUrl(username), encodeUrl(repositoryName));
//
//        connect(apiurl, null);
//    }

    private void connect(String url, Connector.Callback callback) {
        Connector connector = new VolleyConnector();
        connector.connect(url, callback);
    }

    private String encodeUrl(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }
}
