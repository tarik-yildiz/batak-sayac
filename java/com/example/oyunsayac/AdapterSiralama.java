package com.example.oyunsayac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterSiralama extends RecyclerView.Adapter<AdapterSiralama.ViewHolder> {
    Context context;
    List<Gamer> gamers;

    public AdapterSiralama(Context context, List<Gamer> gamers) {
        this.context = context;
        this.gamers = gamers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_siralama,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foto.setImageResource(gamers.get(position).getImageId());
        holder.puan.setText(holder.puan.getText()+" "+gamers.get(position).toplamPuan());
        holder.isim.setText(gamers.get(position).getName());
        holder.battigi.setText(holder.battigi.getText()+" "+gamers.get(position).getBatmaSayisi());
        holder.kazandigi.setText((holder.kazandigi.getText()+" "+gamers.get(position).getKazandigiElSayisi()));
    }

    @Override
    public int getItemCount() {
        return gamers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView foto;
        TextView isim,puan,kazandigi,battigi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.siralamaImageView);
            isim=itemView.findViewById(R.id.siralamaIsimText);
            puan=itemView.findViewById(R.id.siralamaPuanText);
            kazandigi=itemView.findViewById(R.id.siralamaKazandigiElText);
            battigi=itemView.findViewById(R.id.siralamaBattigiElText);
        }
    }
}
