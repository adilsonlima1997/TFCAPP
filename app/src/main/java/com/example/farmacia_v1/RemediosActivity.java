package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Base_dados.DBProdutos;

public class RemediosActivity extends AppCompatActivity {

    RecyclerView recView;
    FloatingActionButton btn_add_remedios;
    DBProdutos dbProdutos;
    ArrayList<String> remedio_id, remedio_nome, remedio_quantidade, remedio_descricao;
    CUstomerAdapter customerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedios);

        recView = findViewById(R.id.recyclerview);
        btn_add_remedios = findViewById(R.id.add_button);

        btn_add_remedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_Remedios_Activity.class);
                startActivity(intent);
            }
        });

        dbProdutos = new DBProdutos(RemediosActivity.this);
        remedio_id = new ArrayList<>();
        remedio_nome = new ArrayList<>();
        remedio_quantidade = new ArrayList<>();
        remedio_descricao = new ArrayList<>();

        displayData();
        customerAdapter = new CUstomerAdapter(RemediosActivity.this, remedio_id,remedio_nome,remedio_quantidade,remedio_descricao);
        recView.setAdapter(customerAdapter);
        recView.setLayoutManager(new LinearLayoutManager(RemediosActivity.this));
    }

    public void displayData(){
        Cursor cursor = dbProdutos.readAllData();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "Nenhum Dado para mostrar", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                remedio_id.add(cursor.getString(0));
                remedio_nome.add(cursor.getString(1));
                remedio_quantidade.add(cursor.getString(2));
                remedio_descricao.add(cursor.getString(3));
            }
        }
    }
}