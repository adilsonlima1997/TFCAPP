package com.example.farmacia_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ComporEmailActivity extends AppCompatActivity {

    EditText email, _subject,_body;
    Button btn_send;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compor_email);

        email = findViewById(R.id.emailAddress);
        _subject = findViewById(R.id.subject);
        _body = findViewById(R.id.multEmail);
        back = findViewById(R.id._back);
        btn_send = findViewById(R.id.send);

        //a função desse botão é enviar um email
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verifica se os campos estão todos preenchidos de acordo com o que está a ser pedido
                if (!email.getText().toString().isEmpty() && !_subject.getText().toString().isEmpty() && !_body.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, _subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, _body.getText().toString());
                    //---intent.setType("message/rfc822");----
                    intent.setData(Uri.parse("mailto:"));
                    //verificar se tem algum app no telemovel que pode executar a activity senão lança uma mensagem de erro
                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(ComporEmailActivity.this, "Não aplicativo que suporta essa ação.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ComporEmailActivity.this, "Por favor preencher os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}