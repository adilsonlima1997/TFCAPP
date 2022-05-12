package com.example.farmacia_v1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CUstomerAdapter extends RecyclerView.Adapter<CUstomerAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList remedio_id, remedio_nome, remedio_quantidade, remedio_descricao;


    CUstomerAdapter(Activity activity, Context context, ArrayList remedio_id, ArrayList remedio_nome,ArrayList remedio_quantidade,ArrayList remedio_descricao){
        this.activity = activity;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.remedio_id_txt.setText(String.valueOf(remedio_id.get(position)));
        holder.remedio_nome_txt.setText(String.valueOf(remedio_nome.get(position)));
        holder.remedio_quantidade_txt.setText(String.valueOf(remedio_quantidade.get(position)));
        holder.remedio_descricao_txt.setText(String.valueOf(remedio_descricao.get(position)));
        holder._mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("remedio_id", String.valueOf(remedio_id.get(position)));
                intent.putExtra("remedio_nome", String.valueOf(remedio_nome.get(position)));
                intent.putExtra("remedio_quantidade", String.valueOf(remedio_quantidade.get(position)));
                intent.putExtra("remedio_descricao", String.valueOf(remedio_descricao.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return remedio_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView remedio_id_txt, remedio_nome_txt, remedio_quantidade_txt, remedio_descricao_txt;
        LinearLayout _mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            remedio_id_txt = itemView.findViewById(R.id.id_remedio);
            remedio_nome_txt = itemView.findViewById(R.id.nome_remedio);
            remedio_quantidade_txt = itemView.findViewById(R.id.quan_remedio);
            remedio_descricao_txt = itemView.findViewById(R.id.des_remedio);
            _mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
