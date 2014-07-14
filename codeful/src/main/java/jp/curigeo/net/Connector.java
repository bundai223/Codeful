package jp.curigeo.net;

/**
 * Created by nishimuradaiji on 2014/07/14.
 */
public interface Connector {
   /**
     * Http通信（完了コールバックつき）
     * @param url
     * @param callback
     */
    public void connect(String url, ConnectCallback callback);
 }
