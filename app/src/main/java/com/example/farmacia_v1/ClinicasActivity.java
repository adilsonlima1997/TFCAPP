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
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import Base_dados.DBClinicas;
import Base_dados.DBFarmacias;

public class ClinicasActivity extends AppCompatActivity {

    RecyclerView recyclerView_1;
    FloatingActionButton btn_add_1;
    DBClinicas myDB;
    ArrayList<String> id_clinica, nome_clinica, local_clinica, hora_clinica;
    CustomerAdapterClinica customerAdapterClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinicas);

        recyclerView_1 = findViewById(R.id.recyclerview_2);
        btn_add_1 = findViewById(R.id.ad_button1);

        btn_add_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClinicasActivity.this, Add_clinicasActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBClinicas(ClinicasActivity.this);
        id_clinica = new ArrayList<>();
        nome_clinica = new ArrayList<>();
        local_clinica = new ArrayList<>();
        hora_clinica = new ArrayList<>();

        displayData();

        customerAdapterClinica = new CustomerAdapterClinica(ClinicasActivity.this, this, id_clinica, nome_clinica, local_clinica,hora_clinica);
        recyclerView_1.setAdapter(customerAdapterClinica);
        recyclerView_1.setLayoutManager(new LinearLayoutManager(ClinicasActivity.this));
    }
    //esta função faz um refresh na activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void displayData() {
        Cursor cursor = myDB.readAllData2();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id_clinica.add(cursor.getString(0));
                nome_clinica.add(cursor.getString(1));
                local_clinica.add(cursor.getString(2));
                hora_clinica.add(cursor.getString(3));
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
                DBClinicas myDB = new DBClinicas(ClinicasActivity.this);
                myDB.deleteAllData2();
                //Refresh the recycleview
                Intent intent = new Intent(ClinicasActivity.this,ClinicasActivity.class);
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