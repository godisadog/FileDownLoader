package com.zw.fd.library.persistance;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhenwei on 2017/9/4.
 */

public class DB extends SQLiteOpenHelper{
    public static final String DB_NAME = "down.status";
    public static final int VERSION = 1;


    public DB(Context context, String name, int version) {
        super(context, name, null, version);
    }

    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DownStatusModel.TABLE_NAME + "(" +
            DownStatusModel.ID + " INTEGER PRIMARY KEY ," +
            DownStatusModel.URL + " TEXT ," +
            DownStatusModel.FILE_PATH + " TEXT ," +
            DownStatusModel.STATUS + " INTEGER ," +
            DownStatusModel.SOFAR + " INTEGER, " +
            DownStatusModel.TOTAL + " INTEGER, " +
            DownStatusModel.ETAG + " TEXT, " +
            DownStatusModel.ERR_MSG + " TEXT"
            + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO 暂无
    }
}
