package com.example.esameits2019;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esameits2019.R;
import com.example.esameits2019.data.AttivitaTableHelper;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.data.UtentiTableHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class New02 extends AppCompatActivity {

    EditText etNome,
            etCognome,
            etEta,
            etEmal;

    Button btnSalva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new02);
        setTitle("Nuovo Utente");

        etNome = findViewById(R.id.et_nome);
        etCognome = findViewById(R.id.et_cognome);
        etEmal = findViewById(R.id.et_email);
        etEta = findViewById(R.id.et_eta);

        btnSalva = findViewById(R.id.btn_salva_user);

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }


    public void insert() {

        ContentValues values = new ContentValues();
        values.put(UtentiTableHelper.NOME, etNome.getText().toString());
        values.put(UtentiTableHelper.COGNOME, etCognome.getText().toString());
        values.put(UtentiTableHelper.ETA, Integer.parseInt(etEta.getText().toString()));
        values.put(UtentiTableHelper.EMAIL, etEmal.getText().toString());

        getContentResolver().insert(DBProvider.UTENTI_URI, values);

        finish();
    }
}
