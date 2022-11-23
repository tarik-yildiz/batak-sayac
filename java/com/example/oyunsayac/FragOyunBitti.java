package com.example.oyunsayac;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

public class FragOyunBitti extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView mode,tursayisi,kazanan,enazpuanalan;

    AdapterSiralama adapterSiralama;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oyun_bitti, container, false);
        tanimla();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void tanimla() {
        recyclerView = view.findViewById(R.id.recyclerOyunBitti);
        mode=view.findViewById(R.id.oyunmoduText);
        tursayisi=view.findViewById(R.id.tursayisiText);
        kazanan=view.findViewById(R.id.kazananOyuncuText);
        enazpuanalan=view.findViewById(R.id.enazpuanText);
        setAdapter();
        settexts();
        sirala();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setAdapter() {
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager1);
        adapterSiralama = new AdapterSiralama(getContext(), GameController.getInstance().getOyun().getGamers());
        recyclerView.setAdapter(adapterSiralama);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sirala(){
        GameController.getInstance().sirala();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void settexts()
    {
        tursayisi.setText(tursayisi.getText()+" "+GameController.getInstance().getOyun().getNowPlaying());
        kazanan.setText((kazanan.getText()+""+GameController.getInstance().KazananOyuncu().getName()));
        enazpuanalan.setText((enazpuanalan.getText()+""+GameController.getInstance().EnAzPuanAlan().getName()));
        mode.setText(mode.getText()+" "+GameController.getInstance().getOyun().getMod());
    }
}
