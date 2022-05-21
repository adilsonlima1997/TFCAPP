package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Base_dados.DBFarmacias;

public class Add_Farmacias_Activity extends AppCompatActivity {

    EditText name_farmacia, Local_farmacia;
    Button add_farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmacias);

        name_farmacia = findViewById(R.id.nome_farmacia);
        Local_farmacia = findViewById(R.id.localizaco_farmacia);
        add_farmacia = findViewById(R.id.save_bttn);

        //Esta função terá a responsabilidade de adicionar os dados na base de dados
        add_farmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFarmacias myDB = new DBFarmacias(Add_Farmacias_Activity.this);
                myDB.addFarmacias1(name_farmacia.getText().toString().trim(), Local_farmacia.getText().toString().trim());
            }
        });

    }
}