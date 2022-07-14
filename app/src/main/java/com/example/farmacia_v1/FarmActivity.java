package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FarmActivity extends AppCompatActivity {

    ImageView maps_farmacias,_estoque,remedios,list_farm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        remedios = findViewById(R.id.btn_remedios);
        maps_farmacias = findViewById(R.id.maps_farmacia);
        _estoque = findViewById(R.id.estoque);
        list_farm = findViewById(R.id.btn_lista_farmacias);

        remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RemediosActivity.class);
                startActivity(intent);

            }
        });

        _estoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EstoqueActivity.class);
                startActivity(intent);
            }
        });

        list_farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FarmaciasActivity.class);
                startActivity(intent);

            }
        });

        maps_farmacias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=pharmacy");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}