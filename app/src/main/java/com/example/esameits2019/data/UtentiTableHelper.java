package com.example.esameits2019.data;

import android.provider.BaseColumns;

public class UtentiTableHelper implements BaseColumns {

    public static final String TABLE_NAME = "utenti";
    public static final String NOME = "nome";
    public static final String COGNOME = "cognome";
    public static final String ETA = "eta";
    public static final String EMAIL = "email";

    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            NOME + " TEXT , " +
            COGNOME + " TEXT , " +
            ETA + " INTEGER , " +
            EMAIL + " TEXT ) ;";
}
