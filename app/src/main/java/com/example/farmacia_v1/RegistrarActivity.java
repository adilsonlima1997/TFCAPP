package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Base_dados.DBHelper;

public class RegistrarActivity extends AppCompatActivity {

    EditText nome, senha, confirmar_Senha;
    Button Registrar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nome = findViewById(R.id.name);
        senha = findViewById(R.id.password);
        confirmar_Senha = findViewById(R.id.repassword);
        Registrar = findViewById(R.id.btn_resgistar1);
        DB = new DBHelper(this);



        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome1 = nome.getText().toString();
                String senha1 = senha.getText().toString();
                String confirmar_senha1 = confirmar_Senha.getText().toString();

                if (TextUtils.isEmpty(nome1) || TextUtils.isEmpty(senha1) || TextUtils.isEmpty(confirmar_senha1))
                    Toast.makeText(RegistrarActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    if (senha1.equals(confirmar_senha1)){
                        Boolean checkuser = DB.checkusername(nome1);
                        if (checkuser == false){
                            Boolean insert = DB.inserData(nome1, senha1);
                            if (insert == true){
                                Toast.makeText(RegistrarActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegistrarActivity.this, "Falha no Registro", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegistrarActivity.this, "Usuário já existe", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistrarActivity.this, "Palavra pass não está igual", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}