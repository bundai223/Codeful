package com.n1sh6r.net.github;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.n1sh6r.net.Connector;
import com.n1sh6r.net.VolleyConnector;
import com.n1sh6r.util.Logger;

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

    private final String API_URL_BASE = "https://api.github.com";

    public ApiManager() {
    }

    /**
     * ユーザーを検索する。
     * @param keyword
     * @param callback
     * @throws UnsupportedEncodingException
     */
    public void requestSearchUser(String keyword, final Callback<ResponseSearchUser> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("%s/search/users?q=%s", API_URL_BASE, encodeUrl(keyword));

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

    /**
     * リポジトリーを検索する。
     * @param keyword
     * @param callback
     * @throws UnsupportedEncodingException
     */
    public void requestSearchRepository(String keyword, final Callback<ResponseSearchRepository> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("%s/search/repositories?q=%s", API_URL_BASE, encodeUrl(keyword));

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

    /**
     * ユーザーのリポジトリー一覧を取得する。
     * @param username
     * @param callback
     * @throws UnsupportedEncodingException
     */
    public void requestlistupUserRepository(String username, final Callback<ResponseSearchRepository> callback) throws UnsupportedEncodingException {
        String apiurl = String.format("%s/users/%s/repos", API_URL_BASE, encodeUrl(username));

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

    /**
     * リポジトリをダウンロードする処理
     * @param url
     * @throws UnsupportedEncodingException
     */
    public void requestGetUserRepository(String url) throws UnsupportedEncodingException {
//        String apiurl = String.format("%s/repos/%s/%s/zipall", API_URL_BASE, encodeUrl(username), encodeUrl(repositoryName));
//        connect(apiurl, null);
    }

    /**
     * リポジトリのブランチをzipでダウンロードする。
     * @param username
     * @param reposName
     * @param branch
     */
    public void requestGetUserRepositoryBranch(String username, String reposName, String branch, final Callback<String> callback) throws UnsupportedEncodingException {
        String apiurl = getRepositoryZipUrl(username, reposName, branch);

        connect(apiurl, new Connector.Callback() {
            @Override
            public void onComplete(String response) {
                callback.onComplete(response);
            }

            @Override
            public void onCompleteWithError() {
                callback.onError();
            }
        });
    }
    public String getRepositoryZipUrl(String username, String reposName, String branch) throws UnsupportedEncodingException {
        return String.format("http://github.com/%s/%s/archive/%s.zip", encodeUrl(username), encodeUrl(reposName), encodeUrl(branch));
    }

    /**
     *  接続処理の根幹
     * @param url
     * @param callback
     */
    private void connect(String url, Connector.Callback callback) {
        Connector connector = new VolleyConnector();
        connector.connect(url, callback);
    }

    private String encodeUrl(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }
}
