package jp.curigeo.codeful;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

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

    public static String getDownloadRepositoryPath(Context context) {
        String path;
        { // external
            String mediaState = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(mediaState)) {
            }
            File dirpath = Environment.getExternalStorageDirectory();
            path = dirpath.getPath() + "/" + context.getPackageName();
        }

//            { // internal
//                File dirpath = Environment.getDataDirectory();
//                path = dirpath.getPath() + "/" + getActivity().getPackageName();
//            }
        return path;
    }
}
