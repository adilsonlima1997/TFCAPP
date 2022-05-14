package com.example.farmacia_v1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Base_dados.DBProdutos;

public class RemediosActivity extends AppCompatActivity {

    RecyclerView recView;
    ImageView empty;
    TextView no_data;
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
        empty = findViewById(R.id.empty_box);
        no_data = findViewById(R.id.textView5);

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
        customerAdapter = new CUstomerAdapter(RemediosActivity.this, this,remedio_id, remedio_nome,remedio_quantidade,remedio_descricao);
        recView.setAdapter(customerAdapter);
        recView.setLayoutManager(new LinearLayoutManager(RemediosActivity.this));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apagar Tudo!");
        builder.setMessage("Desejas apagar todos os produtos ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBProdutos myDB = new DBProdutos(RemediosActivity.this);
                myDB.deleteAllData();
                //Refresh the recycleview
                Intent intent = new Intent(RemediosActivity.this,RemediosActivity.class);
                startActivity(intent);
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