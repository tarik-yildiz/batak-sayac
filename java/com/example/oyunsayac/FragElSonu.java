package com.example.oyunsayac;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class FragElSonu extends Fragment {
    EditText oyuncu1, oyuncu2, oyuncu3, oyuncu4;
    View view;
    Button go;
    Context context;
    boolean elArttiMi;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.el_sonu, container, false);
        context = getContext();
        if (getArguments() != null) {
            Toast.makeText(getContext(), "sddafs", Toast.LENGTH_SHORT).show();
        }
        tanimla();
        isimlendir();
        return view;
    }

    void tanimla() {
        oyuncu1 = view.findViewById(R.id.oyuncu1);
        oyuncu2 = view.findViewById(R.id.oyuncu2);
        oyuncu3 = view.findViewById(R.id.oyuncu3);
        oyuncu4 = view.findViewById(R.id.oyuncu4);
        go = view.findViewById(R.id.goButton);
        setOnClick();

    }

    void setOnClick() {
        go.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (editTextKontrolu()) {
                    setDatas();
                //    FragmentKontrol.getInstance().FragmentiKapat(getContext(), Constants.END_OF_TOUR_TAG);
                    roundEkle();
                    if (GameController.getInstance().isElliIki()){
                        FragmentKontrol.getInstance().FragmentReplace(context,new FragOyunBitti(),Constants.END_OF_THE_GAME_TAG);
                    }else{
                        FragmentKontrol.getInstance().FragmentReplace(context,new FragGame(),Constants.GAME_SCREEN_TAG);
    //                    FragmentKontrol.getInstance().YeniFragmentAc(context, new FragGame(), Constants.GAME_SCREEN_TAG);
                    }

                } else {
                    Toast.makeText(getContext(), "Lütfen oyundaki bütün oyuncuların aldığı kart sayısını girin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void isimlendir() {
        oyuncu1.setHint("{ " + GameController.getInstance().getOyun().getGamers().get(0).getName().toUpperCase(Locale.ROOT) + " } adlı oyuncunun puanı");
        oyuncu2.setHint("{ " + GameController.getInstance().getOyun().getGamers().get(1).getName().toUpperCase(Locale.ROOT) + " } adlı oyuncunun puanı");
        oyuncu3.setHint("{ " + GameController.getInstance().getOyun().getGamers().get(2).getName().toUpperCase(Locale.ROOT) + " } adlı oyuncunun puanı");
        if (GameController.getInstance().getOyun().getGamers().size()==4)
        oyuncu4.setHint("{ " + GameController.getInstance().getOyun().getGamers().get(3).getName().toUpperCase(Locale.ROOT) + " } adlı oyuncunun puanı");
        else
            oyuncu4.setEnabled(false);
    }

    public void roundEkle() {
        GameController.getInstance().getOyun().getRounds()
                .get(GameController.getInstance().getOyun().getNowPlaying()).yazdir();
        GameController.getInstance().getOyun().elArttir();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void setDatas() {
        switch (GameController.getInstance().getOyun().oyuncuSayisi()) {
            case 4:
                int oyuncudort = Integer.parseInt(oyuncu4.getText().toString());
                GameController.getInstance().setElliIki(GameController.getInstance().OyuncularinSkorlariniKaydet(3, oyuncudort));
            case 3:
                int oyuncuuc = Integer.parseInt(oyuncu3.getText().toString());
                GameController.getInstance().setElliIki(GameController.getInstance().OyuncularinSkorlariniKaydet(2, oyuncuuc));
                int oyuncuiki = Integer.parseInt(oyuncu2.getText().toString());
                GameController.getInstance().setElliIki(GameController.getInstance().OyuncularinSkorlariniKaydet(1, oyuncuiki));
                int oyuncubir = Integer.parseInt(oyuncu1.getText().toString());
                GameController.getInstance().setElliIki(GameController.getInstance().OyuncularinSkorlariniKaydet(0, oyuncubir));
                GameController.getInstance().kimkazandi();
                break;
        }

    }

    boolean editTextKontrolu() {
        switch (GameController.getInstance().getOyun().oyuncuSayisi()) {
            case 4:
                if (oyuncu4.getText().toString().trim().equals("")) {
                    return false;
                }
            case 3:
                if (oyuncu3.getText().toString().trim().equals("")) {
                    return false;
                }
                if (oyuncu2.getText().toString().trim().equals("") && oyuncu1.getText().toString().trim().equals("")) {
                    return false;
                } else {
                    return true;
                }
            default:
                return false;
        }
    }


}
