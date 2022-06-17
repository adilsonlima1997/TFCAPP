package com.example.farmacia_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapterEstoque extends RecyclerView.Adapter<CustomerAdapterEstoque.MyViewHolder>{

    private Context context;
    private ArrayList remedio_id, remedio_nome, remedio_quantidade, remedio_descricao;
    
    CustomerAdapterEstoque(EstoqueActivity estoqueActivity, Context context, ArrayList remedio_id, ArrayList remedio_nome, ArrayList remedio_quantidade, ArrayList remedio_descricao){

        this.context = context;
        this.remedio_id = remedio_id;
        this.remedio_nome = remedio_nome;
        this.remedio_quantidade = remedio_quantidade;
        this.remedio_descricao = remedio_descricao;
        
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleview_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.remedio_id_txt.setText(String.valueOf(remedio_id.get(position)));
        holder.remedio_nome_txt.setText(String.valueOf(remedio_nome.get(position)));
        holder.remedio_quantidade_txt.setText(String.valueOf(remedio_quantidade.get(position)));
        holder.remedio_descricao_txt.setText(String.valueOf(remedio_descricao.get(position)));

    }

    @Override
    public int getItemCount() {
        return remedio_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView remedio_id_txt, remedio_nome_txt, remedio_quantidade_txt, remedio_descricao_txt;
        
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            remedio_id_txt = itemView.findViewById(R.id.id_remedio);
            remedio_nome_txt = itemView.findViewById(R.id.nome_remedio);
            remedio_quantidade_txt = itemView.findViewById(R.id.quan_remedio);
            remedio_descricao_txt = itemView.findViewById(R.id.des_remedio);
        }
    }
}
