package jp.curigeo.codeful.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nishimuradaiji on 2014/08/13.
 */
public class Database extends SQLiteOpenHelper {

    enum Columns {
        RepositoryName,
        OwnerName,
        Id,
        ForkedCount,
        StarredCount,
        SourceUrl,
        SourcePath,
    }

    ;


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableRepository(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * リポジトリのDBを作成する。
     *
     * @param db
     */
    private void createTableRepository(SQLiteDatabase db) {
        // TODO: 仮
        final String TABLE_NAME = "repositories";
        final String TableColumnsKey = "test";

        String talk = "create table if not exists " + TABLE_NAME + "("
                + TableColumnsKey + " text not null"
                + ");";
        db.execSQL(talk);
    }

}
