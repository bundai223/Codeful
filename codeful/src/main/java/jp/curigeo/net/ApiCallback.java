package jp.curigeo.net;

/**
 * Created by nishimuradaiji on 2014/07/14.
 */
public interface ApiCallback {
    void onComplete(String response);
    void onCompleteWithError();
}
