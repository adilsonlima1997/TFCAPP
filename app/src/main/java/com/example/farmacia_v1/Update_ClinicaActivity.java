package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Base_dados.DBClinicas;
import Base_dados.DBProdutos;

public class Update_ClinicaActivity extends AppCompatActivity {

    EditText input_clinica_nome, input_clinica_local, input_clinica_horario;
    Button btn_update_clinica;
    String id_clinica, nome_clinica,localizacao_clinica, horario_clinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_clinica);

        input_clinica_nome = findViewById(R.id.input_nome_clinica);
        input_clinica_local = findViewById(R.id.input_localizacao_clinica);
        input_clinica_horario = findViewById(R.id.input_horario_clinica);
        btn_update_clinica = findViewById(R.id.save_clinica_btn);
        getAndSetItentData();

        btn_update_clinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBClinicas myDB = new DBClinicas(Update_ClinicaActivity.this);
                nome_clinica = input_clinica_nome.getText().toString().trim();
                localizacao_clinica = input_clinica_local.getText().toString().trim();
                horario_clinica = input_clinica_horario.getText().toString().trim();
                myDB.updateDataClinica(id_clinica,nome_clinica,localizacao_clinica,horario_clinica);
            }
        });
    }

    void getAndSetItentData(){
        if (getIntent().hasExtra("clinica_id") && getIntent().hasExtra("clinica_nome")
                && getIntent().hasExtra("clinica_localizacao") && getIntent().hasExtra("clinica_hora")){

            //Getting Data from Intent
            id_clinica = getIntent().getStringExtra("clinica_id");
            nome_clinica = getIntent().getStringExtra("clinica_nome");
            localizacao_clinica = getIntent().getStringExtra("clinica_localizacao");
            horario_clinica = getIntent().getStringExtra("clinica_hora");

            //Setting Intent Data
            input_clinica_nome.setText(nome_clinica);
            input_clinica_local.setText(localizacao_clinica);
            input_clinica_horario.setText(horario_clinica);
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}