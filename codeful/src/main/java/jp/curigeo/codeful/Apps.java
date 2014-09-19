package jp.curigeo.codeful;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Created by nishimuradaiji on 2014/08/13.
 */
public class Apps extends Application {
    @Override
    public void onCreate() {
    }

    @Override
    public void onTerminate() {
    }

    /**
     * ダウンロードしたリポジトリの保存先パスを返す
     *
     * @param context
     * @return
     */
    public static String getDownloadRepositoryPath(@NonNull final Context context) {
        // external
        String mediaState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(mediaState) == false) {
            // TODO: SDマウントされていない場合の対処
        }
        File dirpath = Environment.getExternalStorageDirectory();
        String path = dirpath.getPath() + "/" + context.getPackageName();

        return path;
    }

    /**
     * Zipファイルのファイル名を取得する
     *
     * @param userName
     * @param repositoryName
     * @param branchName
     * @return
     */
    public static String getDownloadRepositoryName(@NonNull final String userName, @NonNull final String repositoryName, @NonNull final String branchName) {
        String filename = String.format("%s-%s-%s.zip", userName, repositoryName, branchName);
        return filename;
    }

    /**
     * 拡張子を返す
     *
     * @param filePath
     * @return
     */
    public static String getExtention(String filePath) {
        int index = filePath.indexOf('.');
        if (index < 0) {
            return "";
        } else {
            String ext = filePath.substring(index);
            return ext;
        }
    }
}
