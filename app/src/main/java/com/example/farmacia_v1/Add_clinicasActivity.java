package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import Base_dados.DBClinicas;

public class Add_clinicasActivity extends AppCompatActivity {

    EditText  name_clinica, local_clinica, hora_clinica;
    Button btn_add_clinica;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinicas);

        name_clinica =findViewById(R.id.nome_clinica);
        local_clinica =findViewById(R.id.localizaco_clinica);
        hora_clinica =findViewById(R.id.horario_clinica);
        btn_add_clinica =findViewById(R.id.save_bttn2);



        btn_add_clinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBClinicas myDB = new DBClinicas(Add_clinicasActivity.this);
                myDB.addClinica(name_clinica.getText().toString().trim(), local_clinica.getText().toString().trim(),
                        hora_clinica.getText().toString().trim());
                //Refresh the recycleview
                Intent intent = new Intent(Add_clinicasActivity.this,ClinicasActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }




}