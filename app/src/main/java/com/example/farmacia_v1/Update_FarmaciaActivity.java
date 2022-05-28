package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_FarmaciaActivity extends AppCompatActivity {

    EditText input_farmacia_nome, input_farmacia_local;
    Button btn_update_farmacia;
    String id_farmacia, nome_farmacia,localizacao_farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_farmacia);

        input_farmacia_nome = findViewById(R.id.input_nome_farmacia);
        input_farmacia_local = findViewById(R.id.input_localizacao_farmacia);
        btn_update_farmacia = findViewById(R.id.save_farmacia_btn);

        //first we call this
        getSetIntenteData();

        btn_update_farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}