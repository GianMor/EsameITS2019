package com.example.esameits2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.esameits2019.adapter.List01Adapter;
import com.example.esameits2019.data.AttivitaTableHelper;
import com.example.esameits2019.data.DBProvider;
import com.example.esameits2019.fragments.ConfirmDialogFragment;
import com.example.esameits2019.fragments.ConfirmDialogFragmentListener;

public class List01 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, ConfirmDialogFragmentListener {

    ListView listView;

    Button btnNuovaAttovita;
    Button btnIndietro;

    List01Adapter adapter;

    private static final int ID = 1;

    public static final String ATT_ID = "ATT_ID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list01);

        listView = findViewById(R.id.listview_list01);

        btnNuovaAttovita = findViewById(R.id.btn_nuova_attivita_list01);
        btnIndietro = findViewById(R.id.btn_indietro_list01);

        adapter = new List01Adapter(this,null);

        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(ID, null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(List01.this, Mod01.class);
                intent.putExtra(ATT_ID,id);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment("ATTENZIONE", "Sei sicuro di voler cancellare l'attività?", id);
                dialogFragment.show(fragmentManager, ConfirmDialogFragment.class.getName());
                return true;
            }
        });

        btnIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List01.this,MainActivity.class));
            }
        });

        btnNuovaAttovita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List01.this, New01.class));
            }
        });


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, DBProvider.ATTIVITA_URI, null, null, null, null);
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
        getContentResolver().delete(DBProvider.ATTIVITA_URI, AttivitaTableHelper._ID + "=" + id, null);
    }

    @Override
    public void onNegativePressed() {
        Toast.makeText(this, "Operazione annullata", Toast.LENGTH_LONG).show();
    }
}
