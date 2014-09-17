package jp.curigeo.codeful.broadcast;

import android.app.DownloadManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;

import jp.curigeo.util.Logger;

/**
 * ダウンロード完了時のブロードキャストレシーバー
 */
public class DownloadBroadcastReceiver extends BroadcastReceiver {
    public DownloadBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);

            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor cursor = manager.query(query);

            if (cursor.moveToFirst() == false) {
                // ダウンロードキャンセル
                Logger.d("ダウンロードマネージャでダウンロードがキャンセルされました。");
                return;
            }

            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            int reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));

            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                // ダウンロード成功
                Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
                Logger.d("ダウンロード成功：id = " + id + ", status = " + status + ", reason = " + reason);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                Notification.Builder builder = new Notification.Builder(context);
                Notification notification = builder.setContentTitle("Codeful").setContentText("リポジトリのダウンロード完了").setTicker(uri.getLastPathSegment()).getNotification();
                notificationManager.notify(0, notification);
            } else {
                Logger.d("ダウンロード失敗：id = " + id + ", status = " + status + ", reason = " + reason);
            }
        }
    }
}
