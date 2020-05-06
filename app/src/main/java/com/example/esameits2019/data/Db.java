package com.example.esameits2019.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {


    public static final String DB_NAME = "todo.db";
    public static final int VERSION = 1;

    public Db(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(AttivitaTableHelper.CREATE);
        db.execSQL(UtentiTableHelper.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
