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

public class Update_FarmaciaActivity extends AppCompatActivity {

    EditText input_farmacia_nome, input_farmacia_local;
    Button btn_update_farmacia, btn_delete_farmacia;
    String id_farmacia, nome_farmacia,localizacao_farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_farmacia);

        input_farmacia_nome = findViewById(R.id.input_nome_farmacia);
        input_farmacia_local = findViewById(R.id.input_localizacao_farmacia);
        btn_update_farmacia = findViewById(R.id.save_farmacia_btn);
        btn_delete_farmacia = findViewById(R.id.delete_farmacia_btn);

        //first we call this
        getSetIntenteData();
        //change our ation bar name
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(nome_farmacia);
        }


        btn_update_farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBFarmacias myDB = new DBFarmacias(Update_FarmaciaActivity.this);
                nome_farmacia = input_farmacia_nome.getText().toString().trim();
                localizacao_farmacia = input_farmacia_local.getText().toString().trim();
                myDB.updateData1(id_farmacia,nome_farmacia,localizacao_farmacia);
                finish();

            }
        });

        btn_delete_farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
                finish();
            }
        });

    }

    void getSetIntenteData(){
        if (getIntent().hasExtra("farmacia_id") && getIntent().hasExtra("farmacia_nome")
                && getIntent().hasExtra("farmacia_localizacao")){

            //Getting Data from Intent
            id_farmacia = getIntent().getStringExtra("farmacia_id");
            nome_farmacia = getIntent().getStringExtra("farmacia_nome");
            localizacao_farmacia = getIntent().getStringExtra("farmacia_localizacao");

            //Setting Intent Data
            input_farmacia_nome.setText(nome_farmacia);
            input_farmacia_local.setText(localizacao_farmacia);
        }else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar " + nome_farmacia + " ?");
        builder.setMessage("Desejas apagar " + nome_farmacia + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBFarmacias MyBD = new DBFarmacias(Update_FarmaciaActivity.this);
                MyBD.deleteOneRow1(id_farmacia);
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