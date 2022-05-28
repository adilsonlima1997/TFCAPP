package com.example.farmacia_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class CustomerAdapterFarmacia extends RecyclerView.Adapter<CustomerAdapterFarmacia.MyViewHolder> {

    private Context context;
    private ArrayList farmacia_id, farmacia_nome, farmacia_localizacao;



    CustomerAdapterFarmacia(Context context, ArrayList farnacia_id, ArrayList farmacia_nome,ArrayList farmacia_localizacao){
        this.context = context;
        this.farmacia_id = farnacia_id;
        this.farmacia_nome = farmacia_nome;
        this.farmacia_localizacao = farmacia_localizacao;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_farmacias,parent, false);
        return new CustomerAdapterFarmacia.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.farmacia_id_txt.setText(String.valueOf(farmacia_id.get(position)));
        holder.farmacia_nome_txt.setText(String.valueOf(farmacia_nome.get(position)));
        holder.localização_farmacia_txt.setText(String.valueOf(farmacia_localizacao.get(position)));
        holder._mainLayoutFarmacia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_FarmaciaActivity.class);
                intent.putExtra("farmacia_id", String.valueOf(farmacia_id.get(position)));
                intent.putExtra("farmacia_nome", String.valueOf(farmacia_nome.get(position)));
                intent.putExtra("farmacia_localizacao", String.valueOf(farmacia_localizacao.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return farmacia_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView farmacia_id_txt, farmacia_nome_txt, localização_farmacia_txt;
        LinearLayout _mainLayoutFarmacia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            farmacia_id_txt = itemView.findViewById(R.id.id_FARMACIA);
            farmacia_nome_txt = itemView.findViewById(R.id.nome_FARMACIA);
            localização_farmacia_txt = itemView.findViewById(R.id.Localizacao_Farmacia);
            _mainLayoutFarmacia = itemView.findViewById(R.id.mainLayoutFarmacia);

        }
    }
}
