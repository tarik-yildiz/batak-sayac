package com.example.oyunsayac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        manager = getSupportFragmentManager();
        FragmentKontrol.getInstance().setManager(getSupportFragmentManager());
        FragmentKontrol.getInstance().FragmentReplace(manager, new SettingsFragment(), "ayarlar");

    }

}