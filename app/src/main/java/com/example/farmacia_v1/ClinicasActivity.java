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
import Base_dados.DBClinicas;

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
        customerAdapterClinica = new CustomerAdapterClinica(ClinicasActivity.this, id_clinica, nome_clinica, local_clinica,hora_clinica);
        recyclerView_1.setAdapter(customerAdapterClinica);
        recyclerView_1.setLayoutManager(new LinearLayoutManager(ClinicasActivity.this));
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
}