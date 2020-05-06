package com.example.esameits2019.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBProvider extends ContentProvider {

    public static final String AUTORITY = "com.example.esameits2019.data.ContentProvider";

    public static final String BASE_PATH_ATTIVITA = "attivita";
    public static final String BASE_PATH_UTENTI = "utenti";

    public static final int ALL_ATTIVITA = 1;
    public static final int SINGLE_ATTIVITA = 0;
    public static final int ALL_UTENTI = 2;
    public static final int SINGLE_UTENTE = 3;


    public static final String MIME_TYPE_ATTIVITA = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_attivita";
    public static final String MIME_TYPE_SINGLE_ATTIVITA = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_attivita";
    public static final String MIME_TYPE_UTENTI = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_utenti";
    public static final String MIME_TYPE_UTENTE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_utente";

    public static final Uri ATTIVITA_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTORITY
            + "/" + BASE_PATH_ATTIVITA);
    public static final Uri UTENTI_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTORITY
            + "/" + BASE_PATH_UTENTI);


    private Db database;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTORITY, BASE_PATH_ATTIVITA, ALL_ATTIVITA);
        uriMatcher.addURI(AUTORITY, BASE_PATH_ATTIVITA + "/#", SINGLE_ATTIVITA);
        uriMatcher.addURI(AUTORITY, BASE_PATH_UTENTI, ALL_UTENTI);
        uriMatcher.addURI(AUTORITY, BASE_PATH_UTENTI + "/#", SINGLE_UTENTE);
    }


    @Override
    public boolean onCreate() {
        database = new Db(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = database.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ATTIVITA:
                builder.setTables(AttivitaTableHelper.TABLE_NAME);
                builder.appendWhere(AttivitaTableHelper._ID + " = " + uri.getLastPathSegment());
                break;
            case ALL_ATTIVITA:
                builder.setTables(AttivitaTableHelper.TABLE_NAME);
                break;
            case SINGLE_UTENTE:
                builder.setTables(UtentiTableHelper.TABLE_NAME);
                builder.appendWhere(UtentiTableHelper._ID + " = " + uri.getLastPathSegment());
                break;
            case ALL_UTENTI:
                builder.setTables(UtentiTableHelper.TABLE_NAME);
                break;
        }
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case SINGLE_ATTIVITA:
                return MIME_TYPE_SINGLE_ATTIVITA;
            case ALL_ATTIVITA:
                return MIME_TYPE_ATTIVITA;
            case SINGLE_UTENTE:
                return MIME_TYPE_UTENTE;
            case ALL_UTENTI:
                return MIME_TYPE_UTENTI;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == ALL_ATTIVITA) {
            SQLiteDatabase db = database.getWritableDatabase();
            long result = db.insert(AttivitaTableHelper.TABLE_NAME, null, values);
            String resultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_ATTIVITA + "/" + result;
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(resultString);
        } else if (uriMatcher.match(uri) == ALL_UTENTI) {
            SQLiteDatabase db = database.getWritableDatabase();
            long result = db.insert(UtentiTableHelper.TABLE_NAME, null, values);
            String resultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_UTENTI+ "/" + result;
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(resultString);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ATTIVITA:
                table = AttivitaTableHelper.TABLE_NAME;
                query = AttivitaTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_ATTIVITA:
                table = AttivitaTableHelper.TABLE_NAME;
                query = selection;
                break;
            case SINGLE_UTENTE:
                table = UtentiTableHelper.TABLE_NAME;
                query = UtentiTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_UTENTI:
                table = UtentiTableHelper.TABLE_NAME;
                query = selection;
                break;
        }
        int deletedRows = db.delete(table, query, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return deletedRows;    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ATTIVITA:
                table = AttivitaTableHelper.TABLE_NAME;
                query = AttivitaTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_ATTIVITA:
                table = AttivitaTableHelper.TABLE_NAME;
                query = selection;
                break;
            case SINGLE_UTENTE:
                table = UtentiTableHelper.TABLE_NAME;
                query = UtentiTableHelper._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_UTENTI:
                table = UtentiTableHelper.TABLE_NAME;
                query = selection;
                break;
        }
        int updatedRows = db.update(table, values, query, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return updatedRows;    }
}
