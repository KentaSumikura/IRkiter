package com.example.irkiter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by owner on 2017/06/22.
 */

public class IRkitterDBOpenHelper extends SQLiteOpenHelper
{
    private  static final String TAG = "ContactDBOpenHelper";

    static final String DATABASE_NAME = "irkitter.db";
    static final int DATABASE_VERSION = 1;

    public ContactDBOpenHeler (Context context)
    {
        //データベースファイル名とバージョンを指定しSQLiteOpenHelperクラスを初期化
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ContactDBOpenHelperのコンストラクタが呼ばれました");
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        Log.d(TAG, "ContactDBOpenHelper.onCreateが呼ばれました");
        //irkitテーブルを生成
        database.execSQL("CREATE TABLE irkit("
            + "redid integer primary key autoincrement,"
            + "irname text not null,");

        //infraredテーブルを生成
        database.execSQL("CREATE TABLE infrared("
                + "irid integer primary key autoincrement,"
                + "redpattern text not null,");

        //iconテーブルを生成
        database.execSQL("CREATE TABLE icon("
                + "iconid integer primary key autoincrement,"
                + "url text not null,");

        //orderテーブルを生成
        database.execSQL("CREATE TABLE order("
                + "irid integer primary key ,"
                + "redid integer primary key ,"
                + "irname text not null,"
                + "foreign key ()");

    }

}
