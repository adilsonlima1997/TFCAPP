package com.example.farmacia_v1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Base_dados.DBProdutos;

public class UpdateActivity extends AppCompatActivity {

    EditText nome_remedio_input, quant_remedio_input, desc_remedio_input;
    Button btn_update, btn_delete;
    String id, nome, quant, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nome_remedio_input = findViewById(R.id.input_nome_remedio2);
        quant_remedio_input = findViewById(R.id.quantidade_remedio2);
        desc_remedio_input = findViewById(R.id.descricao_remedio2);
        btn_update = findViewById(R.id.update_btn);
        btn_delete = findViewById(R.id.delete_btn);

        //first we call this
        getAnsSetIntentData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DBProdutos myDB = new DBProdutos(UpdateActivity.this);
                nome = nome_remedio_input.getText().toString().trim();
                quant = quant_remedio_input.getText().toString().trim();
                desc = desc_remedio_input.getText().toString().trim();
                myDB.updateData(id,nome,quant,desc);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               confirmDialog();
            }
        });

    }
        void getAnsSetIntentData(){
            if(getIntent().hasExtra("remedio_id") && getIntent().hasExtra("remedio_nome") &&
                    getIntent().hasExtra("remedio_quantidade") && getIntent().hasExtra("remedio_descricao")){
                //Getting Data from Intent
                id = getIntent().getStringExtra("remedio_id");
                nome = getIntent().getStringExtra("remedio_nome");
                quant = getIntent().getStringExtra("remedio_quantidade");
                desc = getIntent().getStringExtra("remedio_descricao");

                //Setting Intent Data
                nome_remedio_input.setText(nome);
                quant_remedio_input.setText(quant);
                desc_remedio_input.setText(desc);
            }else{
                Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
            }
        }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Apagar " + nome + " ?");
            builder.setMessage("Desejas apagar " + nome + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBProdutos myDB = new DBProdutos(UpdateActivity.this);
                    myDB.deleteOneRow(id);
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
