package com.example.esameits2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class Mod01 extends AppCompatActivity {

    EditText etTitolo,
            etData,
            etDescrizione;

    Spinner spinner;

    Button btnSalva;

    long id;

    //long idUtente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod01);
        setTitle("Modifica attività");

        etTitolo = findViewById(R.id.et_mod_titolo);
        etData = findViewById(R.id.et_mod_data);
        etDescrizione = findViewById(R.id.et_mod_escrizione);

        spinner = findViewById(R.id.spinner_mod_utente);

        btnSalva = findViewById(R.id.btn_mod_salva);

        id = getIntent().getLongExtra(List01.ATT_ID,0);


        Cursor cursor = getContentResolver().query(DBProvider.ATTIVITA_URI, null, AttivitaTableHelper._ID + " = " + id,
                null, null);



        etData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        etTitolo.setText(cursor.getString(cursor.getColumnIndex(AttivitaTableHelper.TITOLO)));
        etDescrizione.setText(cursor.getString(cursor.getColumnIndex(AttivitaTableHelper.DESCRIZIONE)));
        //spinnerUtenti();
/*        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //idUtente = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        btnSalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                startActivity(new Intent(Mod01.this,List01.class));
            }
        });

    }


   /* private void spinnerUtenti() {

        Cursor cursor=getContentResolver().query(DBProvider.ATTIVITA_URI,null,null,null,null);

        idUtente =  cursor.getInt(cursor.getColumnIndex(AttivitaTableHelper.UTENTE_ID));


        List02Adapter adapter=new List02Adapter(this,cursor);
        spinner.setId(idUtente);
        spinner.setAdapter(adapter);
    }
*/

    private void update() {

        ContentValues values = new ContentValues();
        values.put(AttivitaTableHelper.DATA, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        values.put(AttivitaTableHelper.TITOLO, etData.getText().toString());
        values.put(AttivitaTableHelper.DESCRIZIONE, etDescrizione.getText().toString());
        //values.put(AttivitaTableHelper.UTENTE_ID, idUtente);

        getContentResolver().update(DBProvider.ATTIVITA_URI, values, AttivitaTableHelper._ID + "=" + id, null);

        finish();
    }
}
