package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Base_dados.DBProdutos;

public class Add_Remedios_Activity extends AppCompatActivity {

    EditText n_remedio, quant_remedio, descr_remedio;
    Button adicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remedios);

        n_remedio = findViewById(R.id.input_nome_remedio);
        quant_remedio = findViewById(R.id.quantidade_remedio);
        descr_remedio = findViewById(R.id.descricao_remedio);
        adicionar = findViewById(R.id.save_btn);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBProdutos dbRemedios = new DBProdutos(Add_Remedios_Activity.this);
                dbRemedios.addRemedios(n_remedio.getText().toString().trim(),
                            Integer.valueOf(quant_remedio.getText().toString().trim()),
                            descr_remedio.getText().toString().trim());
                //Refresh the recycleview
                Intent intent = new Intent(Add_Remedios_Activity.this,RemediosActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}