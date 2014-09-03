package jp.curigeo.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by nishimuradaiji on 2014/09/03.
 */
public class NotificationCreator {
    /**
     * Notificationを生成する根幹の処理
     *
     * @param context
     * @param id
     * @param title
     * @param text
     * @param tickerText
     * @param smallIconResId
     * @param largeBitmap
     * @param pendingIntent
     * @param soundUri
     */
    private static void create(
            Context context,
            int id,
            String title,
            String text,
            String tickerText,
            int smallIconResId,
            Bitmap largeBitmap,
            PendingIntent pendingIntent,
            Uri soundUri
    ) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        if (title != null) {
            builder.setContentTitle(title);
        }
        if (text != null) {
            builder.setContentText(text);
        }
        if (tickerText != null) {
            builder.setTicker(tickerText);
        }
        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent);
        } else {
            // タップしたら非表示にするためには必ず設定する必要がある。
            builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 0));
        }
        if (largeBitmap != null) {
            builder.setLargeIcon(largeBitmap);
        }
        if (soundUri != null) {
            builder.setSound(soundUri);
        }

        // iconは必須
        builder.setSmallIcon(smallIconResId);

        // ?
        builder.setAutoCancel(true);

        // 通知を発行
        getManager(context).notify(id, builder.build());
    }

    /**
     * Notification削除
     * @param context
     * @param id
     */
    private static void clear(Context context, int id) {
        getManager(context).cancel(id);
    }

    /**
     * 全Notification削除
     * @param context
     */
    private static void clearAll(Context context) {
        getManager(context).cancelAll();
    }

    private static NotificationManager getManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
