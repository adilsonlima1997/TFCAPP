package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import Base_dados.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button reg,log;
    Spinner _spinner;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        reg = findViewById(R.id.btn_registrar);
        log = findViewById(R.id.btn_login);
        _spinner = findViewById(R.id.spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        DB = new DBHelper(this);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1 = user.getText().toString();
                String pass1 = pass.getText().toString();
                String item = _spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(user1) || TextUtils.isEmpty(pass1))
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user1, pass1);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Login com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Falha em Logar", Toast.LENGTH_SHORT).show();
                    }
                }

                /*ira verifica o tipo de usuario*/
                if(user1.equals("admin") && pass1.equals("admin") && item.equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);/*mudar o nome da classe home para admin*/
                    startActivity(intent);
                }else if (user1.equals("admin") && pass1.equals("admin") && item.equals("user")){
                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), RegistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}