package com.n1sh6r.net;

import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by nishimuradaiji on 2014/07/15.
 */
public class LruImageCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> memoryCache;

    public LruImageCache() {
        int maxMemory = (int)(Runtime.getRuntime().maxMemory());
        int cacheSize = maxMemory / 8;

        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            // 使用メモリー
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // KB単位
                return bitmap.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return memoryCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        memoryCache.put(s, bitmap);
    }
}
