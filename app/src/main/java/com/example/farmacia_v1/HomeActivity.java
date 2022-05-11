package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    ImageView FeD, remedios, consul,infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FeD = findViewById(R.id.fitness);
        remedios = findViewById(R.id.btn_remedios);
        consul = findViewById(R.id.consulta);
        infor = findViewById(R.id.informacoes);

        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
            }
        });

        FeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Logout Sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RemediosActivity.class);
                startActivity(intent);
            }
        });

        consul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
                startActivity(intent);
            }
        });
    }
}