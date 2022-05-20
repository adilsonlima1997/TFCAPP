package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClinicasActivity extends AppCompatActivity {

    RecyclerView recyclerView_1;
    FloatingActionButton btn_add_1;

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
    }
}