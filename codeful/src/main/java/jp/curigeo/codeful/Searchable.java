package jp.curigeo.codeful;

/**
 * Created by nishimuradaiji on 2014/08/14.
 */
public interface Searchable {
    boolean search(String key);
    void cancel();
}
