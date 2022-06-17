package com.example.farmacia_v1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Base_dados.DBProdutos;

public class EstoqueActivity extends AppCompatActivity {

    RecyclerView recView;
    ImageView empty;
    TextView no_data;
    DBProdutos dbProdutos;
    ArrayList<String> remedio_id, remedio_nome, remedio_quantidade, remedio_descricao;
    CustomerAdapterEstoque customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        recView = findViewById(R.id.recyclerview);
        empty = findViewById(R.id.empty_box);
        no_data = findViewById(R.id.no_data);

        dbProdutos = new DBProdutos(EstoqueActivity.this);
        remedio_id = new ArrayList<>();
        remedio_nome = new ArrayList<>();
        remedio_quantidade = new ArrayList<>();
        remedio_descricao = new ArrayList<>();

        displayData();
        customerAdapter = new CustomerAdapterEstoque(EstoqueActivity.this, this,remedio_id, remedio_nome,remedio_quantidade,remedio_descricao);
        recView.setAdapter(customerAdapter);
        recView.setLayoutManager(new LinearLayoutManager(EstoqueActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //refresh the activity
        if(requestCode == 1){
            recreate();
        }
    }

    public void displayData(){
        Cursor cursor = dbProdutos.readAllData();

        if (cursor.getCount() == 0){
            empty.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){
                remedio_id.add(cursor.getString(0));
                remedio_nome.add(cursor.getString(1));
                remedio_quantidade.add(cursor.getString(2));
                remedio_descricao.add(cursor.getString(3));
            }
            empty.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}