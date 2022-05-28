package com.example.farmacia_v1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapterClinica extends RecyclerView.Adapter<CustomerAdapterClinica.MyViewHolder>{

    private Context context;
    private ArrayList clinica_id, clinica_nome, clinica_localizacao, clinica_hora;

    CustomerAdapterClinica(Context context,  ArrayList clinica_id,ArrayList clinica_nome, ArrayList clinica_localizacao, ArrayList clinica_hora){
        this.context = context;
        this.clinica_id = clinica_id;
        this.clinica_nome = clinica_nome;
        this.clinica_localizacao = clinica_localizacao;
        this.clinica_hora = clinica_hora;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_clinica, parent, false);
        return new CustomerAdapterClinica.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.clinica_id_txt.setText(String.valueOf(clinica_id.get(position)));
        holder.clinica_nome_txt.setText(String.valueOf(clinica_nome.get(position)));
        holder.clinica_localizacao_txt.setText(String.valueOf(clinica_localizacao.get(position)));
        holder.clinica_hora_txt.setText(String.valueOf(clinica_hora.get(position)));
        holder._mainLayoutClinica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_ClinicaActivity.class);
                intent.putExtra("clinica_id", String.valueOf(clinica_id.get(position)));
                intent.putExtra("clinica_nome", String.valueOf(clinica_nome.get(position)));
                intent.putExtra("clinica_localizacao", String.valueOf(clinica_localizacao.get(position)));
                intent.putExtra("clinica_hora", String.valueOf(clinica_hora.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return clinica_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView clinica_id_txt, clinica_nome_txt, clinica_localizacao_txt, clinica_hora_txt;
        LinearLayout _mainLayoutClinica;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clinica_id_txt  = itemView.findViewById(R.id.id_CLINICA);
            clinica_nome_txt  = itemView.findViewById(R.id.nome_CLINICA);
            clinica_localizacao_txt  = itemView.findViewById(R.id.Localizacao_Clinica);
            clinica_hora_txt  = itemView.findViewById(R.id.horas);
            _mainLayoutClinica= itemView.findViewById(R.id.mainLayoutClinica);
        }
    }
}
