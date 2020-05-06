package com.example.esameits2019.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.esameits2019.R;
import com.example.esameits2019.data.AttivitaTableHelper;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.data.UtentiTableHelper;

public class List02Adapter extends CursorAdapter {

    public List02Adapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list02, null);
        return view;    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String eta = cursor.getString(cursor.getColumnIndex(UtentiTableHelper.ETA));
        String nome = cursor.getString(cursor.getColumnIndex(UtentiTableHelper.NOME));
        String cognome = cursor.getString(cursor.getColumnIndex(UtentiTableHelper.COGNOME));



        TextView tvEta = view.findViewById(R.id.tv_eta),
                tvNome = view.findViewById(R.id.tv_nome_utente_list02),
                tvCognome = view.findViewById(R.id.tv_cognome_utente_list02);

        tvEta.setText(eta);
        tvNome.setText(nome);
        tvCognome.setText(cognome);

    }
}
