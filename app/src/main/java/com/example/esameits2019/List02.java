package com.example.esameits2019;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.esameits2019.adapter.List02Adapter;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.data.UtentiTableHelper;
import com.example.esameits2019.fragments.ConfirmDialogFragment;
import com.example.esameits2019.fragments.ConfirmDialogFragmentListener;

public class List02 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, ConfirmDialogFragmentListener {

    private ListView listView;

    Button btnNuovoUtente;
    Button btnIndietro;

    List02Adapter adapter;

    public static final String USER_ID = "USER_ID";

    private static final int ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list02);

        listView = findViewById(R.id.listview_list02);

        btnNuovoUtente = findViewById(R.id.btn_nuova_utente_list02);
        btnIndietro = findViewById(R.id.btn_indietro_list02);

        adapter = new List02Adapter(this,null);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(List02.this, Mod02.class);
                intent.putExtra(USER_ID,id);
                List02.this.startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment("ATTENZIONE", "Sei sicuro di voler cancellare l'utente?", id);
                dialogFragment.show(List02.this.getSupportFragmentManager(), null);
                return true;
            }
        });


        btnIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List02.this,MainActivity.class));
            }
        });

        btnNuovoUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List02.this, New02.class));
            }
        });


        getSupportLoaderManager().initLoader(ID, null, this);


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, DBProvider.UTENTI_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }

    @Override
    public void onPositivePressed(long id) {
        getContentResolver().delete(DBProvider.UTENTI_URI, UtentiTableHelper._ID + "=" + id, null);
        Toast.makeText(this, "Utente Eliminato", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNegativePressed() {
        Toast.makeText(this, "Operazione annullata", Toast.LENGTH_LONG).show();
    }
}
