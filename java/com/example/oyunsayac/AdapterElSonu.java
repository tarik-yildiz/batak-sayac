package com.example.oyunsayac;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterElSonu extends RecyclerView.Adapter<AdapterElSonu.ViewHolder> {
    Context context;
    Activity activity;
    List<Round> list;


    public AdapterElSonu(Context context, List<Round> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.skor_satiri,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterElSonu.ViewHolder holder, int position) {
        holder.textView.setText(position+1+"==> "+list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.skorSatiriText);
        }
    }
}
