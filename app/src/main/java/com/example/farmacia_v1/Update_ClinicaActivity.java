package com.example.farmacia_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Base_dados.DBClinicas;
import Base_dados.DBFarmacias;
import Base_dados.DBProdutos;

public class Update_ClinicaActivity extends AppCompatActivity {

    EditText input_clinica_nome, input_clinica_local, input_clinica_horario;
    Button btn_update_clinica, btn_delete_clinica;
    String id_clinica, nome_clinica,localizacao_clinica, horario_clinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_clinica);

        input_clinica_nome = findViewById(R.id.input_nome_clinica);
        input_clinica_local = findViewById(R.id.input_localizacao_clinica);
        input_clinica_horario = findViewById(R.id.input_horario_clinica);
        btn_update_clinica = findViewById(R.id.save_clinica_btn);
        btn_delete_clinica= findViewById(R.id.delete_clinica_btn);
        getAndSetItentData();
        //change our ation bar name
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(nome_clinica);
        }

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

        btn_delete_clinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar " + nome_clinica + " ?");
        builder.setMessage("Desejas apagar " + nome_clinica + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBClinicas MyBD = new DBClinicas(Update_ClinicaActivity.this);
                MyBD.deleteOneRow2(id_clinica);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}