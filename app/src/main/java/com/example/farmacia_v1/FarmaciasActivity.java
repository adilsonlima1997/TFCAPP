package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FarmaciasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_add;
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
    }
}