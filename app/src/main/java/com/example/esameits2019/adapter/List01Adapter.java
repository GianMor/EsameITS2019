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

import org.w3c.dom.Text;

public class List01Adapter extends CursorAdapter {

    public List01Adapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list01, null);
        return view;    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String titoloAttivita = cursor.getString(cursor.getColumnIndex(AttivitaTableHelper.TITOLO));

        int id = cursor.getInt(cursor.getColumnIndex(AttivitaTableHelper.UTENTE_ID));

        String nomeUtente = "";
        if (id != 0) {

            Cursor datas = context.getContentResolver().query(DBProvider.UTENTI_URI, new String[]{UtentiTableHelper.NOME,
                            UtentiTableHelper._ID}, UtentiTableHelper._ID + " = " + id,
                    null, null);
            datas.moveToNext();
            if (datas.getCount() >= 1) {
                nomeUtente = datas.getString(datas.getColumnIndex(UtentiTableHelper.NOME));
            }else{
                nomeUtente="utente cancellato";
            }
            datas.close();
        }

        String cognomeUtente = "";
        if (id != 0) {

            Cursor datas = context.getContentResolver().query(DBProvider.UTENTI_URI, new String[]{UtentiTableHelper.COGNOME,
                            UtentiTableHelper._ID}, UtentiTableHelper._ID + " = " + id,
                    null, null);
            datas.moveToNext();
            if (datas.getCount() >= 1) {
                cognomeUtente = datas.getString(datas.getColumnIndex(UtentiTableHelper.COGNOME));
            }else{
                cognomeUtente="utente cancellato";
            }
            datas.close();
        }


        TextView tvTitoloAttivita = view.findViewById(R.id.tv_titolo_attivita),
                tvNomeUtente = view.findViewById(R.id.tv_nome_utente),
                tvCognomeUtente = view.findViewById(R.id.tv_cognome_utente);

        tvTitoloAttivita.setText(titoloAttivita);
        tvNomeUtente.setText(nomeUtente);
        tvCognomeUtente.setText(cognomeUtente);

    }
}
