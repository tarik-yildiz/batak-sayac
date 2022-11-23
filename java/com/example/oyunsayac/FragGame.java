package com.example.oyunsayac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragGame extends Fragment {
    Button elSonu,oyunuBitir;

    View view;

    RecyclerView playersRecycler, skorTablosuRecycler;

    List<Gamer> userList;

    Context context;

    AdapterUser adapter;

    AdapterElSonu adapterElSonu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.game_layout, container, false);
        context = inflater.getContext();
        tanimla();
        setAdapters();
        return view;
    }

    void tanimla() {
        playersRecycler = view.findViewById(R.id.gameUsersRecycler);
        skorTablosuRecycler = view.findViewById(R.id.skorTablosuRecycler);

        elSonu = view.findViewById(R.id.elSonuButton);
        oyunuBitir=view.findViewById(R.id.oyunuBitirButton);

        userList = new ArrayList<Gamer>();

        setOnClick();
    }



    void setOnClick() {
        elSonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!GameController.getInstance().getOyun().oyunBittiMi()){
                    FragmentKontrol.getInstance().FragmentiKapat(context, Constants.GAME_SCREEN_TAG);
                    FragmentKontrol.getInstance().YeniFragmentAc(context, new FragElSonu(), Constants.END_OF_TOUR_TAG);
                    skorTablosuRecycler.scrollToPosition(GameController.getInstance().getOyun().getNowPlaying());
                }else
                    Toast.makeText(context,"OYUN BITTI", Toast.LENGTH_SHORT).show();

            }
        });
        oyunuBitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Toast.makeText(context,"Yapım aşamasında", Toast.LENGTH_SHORT).show();
            oyunuBitirMetod();
            }
        });

    }

    void setAdapters() {
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(context, 4);
        playersRecycler.setLayoutManager(layoutManager1);
        adapter = new AdapterUser(context, GameController.getInstance().getOyun().getGamers());
        playersRecycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(context, 1);
        skorTablosuRecycler.setLayoutManager(layoutManager2);
        adapterElSonu = new AdapterElSonu(context, GameController.getInstance().getOyun().getRounds());
        skorTablosuRecycler.setAdapter(adapterElSonu);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterElSonu.notifyDataSetChanged();
        if(GameController.getInstance().isGameStarted()){
            if (GameController.getInstance().getOyun().oyunBittiMi()){
                Toast.makeText(context, "Bütün turları oynadınız.", Toast.LENGTH_SHORT).show();
                elSonu.setEnabled(false);
                oyunuBitirMetod();
            }
        }
    }

    public void oyunuBitirMetod(){
        FragmentKontrol.getInstance().FragmentiKapat(context,Constants.GAME_SCREEN_TAG);
        FragmentKontrol.getInstance().FragmentReplace(context,new FragOyunBitti(),Constants.END_OF_THE_GAME_TAG);
    }

}
