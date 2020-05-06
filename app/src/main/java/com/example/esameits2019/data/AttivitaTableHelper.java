package com.example.esameits2019.data;

import android.provider.BaseColumns;

public class AttivitaTableHelper implements BaseColumns {


    public static final String TABLE_NAME = "attivita";
    public static final String TITOLO = "titolo";
    public static final String DESCRIZIONE = "descrizione";
    public static final String DATA = "data";
    public static final String UTENTE_ID = "utente_id";

    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            TITOLO + " TEXT , " +
            DESCRIZIONE + " TEXT , " +
            DATA + " TEXT , " +
            UTENTE_ID + " INTEGER ) ;";
}
