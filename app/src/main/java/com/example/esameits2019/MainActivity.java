package com.example.esameits2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnListaAttivita,
            btnListaUtenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListaAttivita = findViewById(R.id.btn_lista_attivita);
        btnListaUtenti = findViewById(R.id.btn_lista_utenti);

        btnListaAttivita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,List01.class));
            }
        });

        btnListaUtenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,List02.class));
            }
        });
    }
}
