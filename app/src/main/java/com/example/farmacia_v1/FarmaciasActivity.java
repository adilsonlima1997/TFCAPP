package com.example.farmacia_v1;

import androidx.annotation.Nullable;
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

import Base_dados.DBFarmacias;

public class FarmaciasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_add;

    DBFarmacias myDB;
    ArrayList<String> id_farmacia, farmacia_nome, farmacia_localizacao;
    CustomerAdapterFarmacia customerAdapterFarmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacias);

        recyclerView = findViewById(R.id.recyclerview_1);
        btn_add = findViewById(R.id.ad_button);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmaciasActivity.this, Add_Farmacias_Activity.class);
                startActivity(intent);
            }
        });

        myDB = new DBFarmacias(FarmaciasActivity.this);
        id_farmacia = new ArrayList<>();
        farmacia_nome = new ArrayList<>();
        farmacia_localizacao = new ArrayList<>();
        displayData();

        customerAdapterFarmacia = new CustomerAdapterFarmacia(FarmaciasActivity.this, this, id_farmacia, farmacia_nome, farmacia_localizacao);
        recyclerView.setAdapter(customerAdapterFarmacia);
        recyclerView.setLayoutManager(new LinearLayoutManager(FarmaciasActivity.this));
    }

    //esta função faz um refresh na activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void displayData(){
        Cursor cursor = myDB.readAllData1();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_farmacia.add(cursor.getString(0));
                farmacia_nome.add(cursor.getString(1));
                farmacia_localizacao.add(cursor.getString(2));
            }
        }
    }
}