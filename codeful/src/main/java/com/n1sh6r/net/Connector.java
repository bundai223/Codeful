package com.n1sh6r.net;

/**
 * Created by nishimuradaiji on 2014/07/14.
 */
public interface Connector {
    public interface Callback {
        void onComplete(String response);
        void onCompleteWithError();
    }

    /**
     * Http通信（完了コールバックつき）
     * @param url
     * @param callback
     */
    public void connect(String url, Callback callback);
 }
