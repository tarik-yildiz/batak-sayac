package com.example.oyunsayac;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {
    List<Gamer> gamers;
    View view;
    Button oyunuBaslat;
    EditText oyuncu, oyuncu2, oyuncu3, oyuncu4;
    Spinner spinnerKacKisi, spinnerTurSayisi;
    RadioGroup radioGroup;
    RadioButton radioButtonPuan, radioButtonTur;
    Context context;

    ArrayAdapter adapter, adapter2;
    ArrayList kisiler, tur;

    String[] isimler;
    int pos, turSayisi;
    boolean elliIki;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oyun_ayarlari, container, false);
        context = inflater.getContext();
        tanimla();
        return view;
    }

    void tanimla() {
        isimler = new String[4];
        kisiler = new ArrayList();
        tur = new ArrayList();
        gamers = new ArrayList<>();

        ekle();
        oyunuBaslat = view.findViewById(R.id.buttonOyunuBaslat);

        oyuncu = view.findViewById(R.id.editTextBirinci);
        oyuncu2 = view.findViewById(R.id.editTextIkinci);
        oyuncu3 = view.findViewById(R.id.editTextUcuncu);
        oyuncu4 = view.findViewById(R.id.editTextDorduncu);

        spinnerKacKisi = view.findViewById(R.id.spinnerOyuncuSayisi);
        spinnerTurSayisi = view.findViewById(R.id.spinnerTurSayisi);
        radioGroup = view.findViewById(R.id.radioGroupMod);
        radioButtonPuan = view.findViewById(R.id.radioButtonPuan);
        radioButtonTur = view.findViewById(R.id.radioButtonTur);

        adapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, kisiler);
        adapter2 = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, tur);

        spinnerKacKisi.setAdapter(adapter);
        spinnerTurSayisi.setAdapter(adapter2);


        setListener();

        oyunuBaslat.setEnabled(false);
    }

    void ekle() {
        for (int i = 4; i < 21; i++) {
            tur.add(i);
        }
        kisiler.add(3);
        kisiler.add(4);
    }

    void setListener() {
        oyunuBaslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kontroller(pos);
            }
        });
        spinnerKacKisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                itemDisabler(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerTurSayisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                turSayisi = position + 4;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonPuan.getId()) {
                    oyunuBaslat.setEnabled(true);
                    elliIki = true;
                    spinnerTurSayisi.setEnabled(false);
                    Toast.makeText(context, "52 puana ilk ulaşan oyuncu kazanır.", Toast.LENGTH_SHORT).show();
                } else if (checkedId == radioButtonTur.getId()) {
                    elliIki = false;
                    oyunuBaslat.setEnabled(true);
                    spinnerTurSayisi.setEnabled(true);
                    Toast.makeText(context, "Turlar bitince en yüksek puanlı oyuncu kazanır.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    void oyunBaslasin() {
        if (elliIki) {
            GameController.getInstance().NewGame(gamers, elliIki);

        } else {
            GameController.getInstance().NewGame(gamers, turSayisi);
        }
        FragmentKontrol.getInstance().FragmentReplace(context, new FragGame(), Constants.GAME_SCREEN_TAG);
    }

    void itemDisabler(int numara) {
        switch (numara) {
            case 0:
                oyuncu.setEnabled(true);
                oyuncu2.setEnabled(true);
                oyuncu3.setEnabled(true);
                oyuncu4.setEnabled(false);
                break;
            case 1:
                oyuncu.setEnabled(true);
                oyuncu2.setEnabled(true);
                oyuncu3.setEnabled(true);
                oyuncu4.setEnabled(true);
                break;
        }
    }

    void kontroller(int numara) {
        switch (numara) {
            case 0:
                if (!oyuncu.getText().toString().equals("") &&
                        !oyuncu2.getText().toString().equals("") &&
                        !oyuncu3.getText().toString().equals("")) {
                    gamers.add(new Gamer(oyuncu.getText().toString().trim(), R.drawable.green));
                    gamers.add(new Gamer(oyuncu2.getText().toString().trim(), R.drawable.black));
                    gamers.add(new Gamer(oyuncu3.getText().toString().trim(), R.drawable.blue));
                    oyunBaslasin();
                } else
                    Toast.makeText(context, "Lütfen oyuncuların isimlerini giriniz.", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (!oyuncu.getText().toString().equals("") &&
                        !oyuncu2.getText().toString().equals("") &&
                        !oyuncu3.getText().toString().equals("") &&
                        !oyuncu4.getText().toString().equals("")) {
                    gamers.add(new Gamer(oyuncu.getText().toString().trim(), R.drawable.green));
                    gamers.add(new Gamer(oyuncu2.getText().toString().trim(), R.drawable.black));
                    gamers.add(new Gamer(oyuncu3.getText().toString().trim(), R.drawable.blue));
                    gamers.add(new Gamer(oyuncu4.getText().toString().trim(), R.drawable.red));
                    oyunBaslasin();
                } else
                    Toast.makeText(context, "Lütfen oyuncuların isimlerini giriniz.", Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
