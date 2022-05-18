package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {

    ImageView voltar, phone;
    Button _phoneCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        voltar = findViewById(R.id.seta_voltar);
        phone = findViewById(R.id.telefone);
        _phoneCall = findViewById(R.id.button2);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
            }
        });

        //função que lhe dá a possibilidade de fazer chamadas apartir do app
        _phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:5925806"));
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:5925806"));
                startActivity(intent);
            }
        });
    }
}