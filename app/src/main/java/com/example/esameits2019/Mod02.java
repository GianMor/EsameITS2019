package com.example.esameits2019;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esameits2019.data.AttivitaTableHelper;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.data.UtentiTableHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mod02 extends AppCompatActivity {

    EditText etNome,
            etCognome,
            etEta,
            etEmal;

    Button btnSalva;

    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod02);
        setTitle("Modifica attività");

        etNome = findViewById(R.id.et_nome_mod);
        etCognome = findViewById(R.id.et_cognome_mod);
        etEmal = findViewById(R.id.et_email_mod);
        etEta = findViewById(R.id.et_eta_mod);


        btnSalva = findViewById(R.id.btn_salva_user_mod);

        id = getIntent().getLongExtra(List02.USER_ID,0);

        Cursor cursor = getContentResolver().query(DBProvider.UTENTI_URI, null, UtentiTableHelper._ID + " = " + id,
                null, null);
        cursor.moveToNext();

        String nome = cursor.getString(cursor.getColumnIndex(UtentiTableHelper.NOME));
        etNome.setText(nome);
        etCognome.setText(cursor.getString(cursor.getColumnIndex(UtentiTableHelper.COGNOME)));
        etEmal.setText(cursor.getString(cursor.getColumnIndex(UtentiTableHelper.EMAIL)));
        int eta = cursor.getInt(cursor.getColumnIndex(UtentiTableHelper.ETA));
        etEta.setText(""+eta);

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivity(new Intent(Mod02.this,List02.class));
            }
        });

    }

    private void update() {

        ContentValues values = new ContentValues();
        values.put(UtentiTableHelper.NOME, etNome.getText().toString());
        values.put(UtentiTableHelper.COGNOME, etCognome.getText().toString());
        values.put(UtentiTableHelper.ETA, etEta.getText().toString());
        values.put(UtentiTableHelper.EMAIL, etEmal.getText().toString());

        getContentResolver().update(DBProvider.UTENTI_URI, values, UtentiTableHelper._ID + "=" + id, null);

        finish();
    }
}
