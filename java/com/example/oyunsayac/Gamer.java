package com.example.oyunsayac;

import java.util.ArrayList;
import java.util.List;

public class Gamer  {
    private String name;
    private int imageId;
    private List<Integer> alinanKartlar;
    private int batmaSayisi;
    private int kazandigiElSayisi;
    public Gamer(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
        alinanKartlar = new ArrayList<>();
        batmaSayisi=0;
        kazandigiElSayisi=0;
    }
    public void kazandi(){
        kazandigiElSayisi++;

    }

    public int getKazandigiElSayisi() {
        return kazandigiElSayisi;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public List<Integer> getAlinanKartlar() {
        return alinanKartlar;
    }

    public void puanEkle(int puan) {
        if (puan<0){
            batmaSayisi++;
        }
        alinanKartlar.add(puan);
    }

    public int toplamPuan() {
        int toplam = 0;
        for (int temp : alinanKartlar) {
            toplam += temp;
        }
        return toplam;
    }

    public int getBatmaSayisi() {
        return batmaSayisi;
    }
}
