package com.example.oyunsayac;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Gamer> gamers;
    private List<Round> rounds;
    private int maxRound, nowPlaying;
    private boolean elliIkiMod;
    String mod;
    public Game(List<Gamer> gamers, int maxRound) {
        this.gamers = gamers;
        this.maxRound = maxRound;
        rounds = new ArrayList<>();
        for (int i=0;i<maxRound;i++){
            rounds.add(new Round(gamers));
        }
        elliIkiMod=false;
        nowPlaying = 0;
        mod="TUR SAYISI ( "+maxRound+" )";
    }
    public Game(List<Gamer> gamers, boolean elliIkideBiterMi) {
        this.gamers = gamers;
        this.maxRound = 21;
        rounds = new ArrayList<>();
        for (int i=0;i<21;i++){
            rounds.add(new Round(gamers));
        }
        elliIkiMod=true;
        nowPlaying = 0;
        mod="PUAN (52)";
    }

    public Game() {

    }

    public String getMod() {
        return mod;
    }

    public boolean isElliIkiMod() {
        return elliIkiMod;
    }

    public void setElliIkiMod(boolean elliIkiMod) {
        this.elliIkiMod = elliIkiMod;
    }

    public List<Gamer> getGamers() {
        return gamers;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public int getNowPlaying() {
        return nowPlaying;
    }

    public void elArttir(){
        if (!oyunBittiMi())
            nowPlaying++;

    }
    public void setGamers(List<Gamer> gamers) {
        this.gamers = gamers;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
    }

    public void setNowPlaying(int nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public int oyuncuSayisi() {
        return gamers.size();

    }
    public boolean oyunBittiMi(){
        if (nowPlaying==maxRound)
        return true;
        else return false;
    }
}
