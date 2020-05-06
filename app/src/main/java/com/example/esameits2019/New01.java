package com.example.esameits2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.esameits2019.adapter.List02Adapter;
import com.example.esameits2019.data.AttivitaTableHelper;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.data.UtentiTableHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class New01 extends AppCompatActivity {

    EditText etTitolo,
        etData,
        etDescrizione;

    Spinner spinner;

    Button btnSalva;

    long idUtente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new01);
        setTitle("Nuova attività");

        etTitolo = findViewById(R.id.et_titolo);
        etData = findViewById(R.id.et_data);
        etDescrizione = findViewById(R.id.et_descrizione);

        spinner = findViewById(R.id.spinner_utente);

        btnSalva = findViewById(R.id.btn_salva);

        Date today = new Date();
        String todayFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(today);

        etData.setText(todayFormat);

        spinnerUtenti();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idUtente = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }

    private void spinnerUtenti() {

        Cursor cursor=getContentResolver().query(DBProvider.UTENTI_URI,null,null,null,null);

        List02Adapter adapter=new List02Adapter(this,cursor);
        spinner.setAdapter(adapter);
    }

    public void insert() {

        ContentValues values=new ContentValues();
        values.put(AttivitaTableHelper.TITOLO, etTitolo.getText().toString());
        values.put(AttivitaTableHelper.DESCRIZIONE, etDescrizione.getText().toString());
        values.put(AttivitaTableHelper.DATA, etData.getText().toString());
        values.put(AttivitaTableHelper.UTENTE_ID, idUtente);


        getContentResolver().insert(DBProvider.ATTIVITA_URI, values);

        finish();
    }
}
