package com.fxy.frame.download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 下载数据库创建帮助类
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class DownDbOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vise_download.db";
    private static final int DATABASE_VERSION = 1;

    DownDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DownDb.RecordTable.CREATE);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
