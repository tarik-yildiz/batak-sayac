package com.example.oyunsayac;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {
    Context context;
    Activity activity;
    List<Gamer> list;

    public AdapterUser(Context context, Activity activity, List<Gamer> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
    }

    public AdapterUser(Context context, List<Gamer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterUser.ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        holder.imageView.setImageResource(list.get(position).getImageId());
        int pos=position;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tp = list.get(pos).toplamPuan();
                Toast.makeText(context, GameController.getInstance().getOyun().
                        getGamers().get(pos).getName()
                        +" = "+tp+ " puan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.userImage);
            textView = itemView.findViewById(R.id.userNickName);

        }

    }
}
