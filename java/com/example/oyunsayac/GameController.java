package com.example.oyunsayac;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameController {
    private static GameController instance = new GameController();
    private Game game;
    private boolean elliIki = false;

    private GameController() {
    }

    public static GameController getInstance() {
        return instance;
    }

    public void NewGame(List<Gamer> gamers, int maxRound) {
        game = new Game(gamers, maxRound);
    }

    public void NewGame(List<Gamer> gamers, boolean elliIki) {
        game = new Game(gamers, elliIki);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean OyuncularinSkorlariniKaydet(int pos, int skor) {
        game.getGamers().get(pos).puanEkle(skor);
        return ElliIkiKontrolEt();
    }

    public Game getOyun() {
        return game;
    }

    public boolean ElliIkiKontrolEt() {
        int puan = game.getGamers().get(0).toplamPuan();
        int puan2 = game.getGamers().get(1).toplamPuan();
        int puan3 = game.getGamers().get(2).toplamPuan();
        int puan4 = 0;
        if (game.getGamers().size() == 4)
            puan4 = game.getGamers().get(3).toplamPuan();

        if (puan >= 52)
            return true;
        if (puan2 >= 52)
            return true;
        if (puan3 >= 52)
            return true;
        return puan4 >= 52;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sirala() {
        game.getGamers().sort(new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                if (o1.toplamPuan() > o2.toplamPuan()) {
                    return -1;
                } else if (o1.toplamPuan() < o2.toplamPuan()) {
                    return 1;
                } else
                    return 0;
            }
        });
    }

    public boolean isElliIki() {
        return elliIki;
    }

    public void setElliIki(boolean elliIki) {
        this.elliIki = elliIki;
    }

    public boolean isGameStarted() {
        return game != null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Gamer KazananOyuncu() {
        sirala();
        return game.getGamers().get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Gamer EnAzPuanAlan() {
        game.getGamers().sort(new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                if (o1.toplamPuan() > o2.toplamPuan()) {
                    return 1;
                } else if (o1.toplamPuan() < o2.toplamPuan()) {
                    return -1;
                } else
                    return 0;
            }
        });
        return game.getGamers().get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Gamer EnFazlaBatan() {
        game.getGamers().sort(new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                if (o1.getBatmaSayisi() > o2.getBatmaSayisi()) {
                    return -1;
                } else if (o1.getBatmaSayisi() < o2.getBatmaSayisi()) {
                    return 1;
                } else
                    return 0;
            }
        });
        return game.getGamers().get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Gamer EnAzBatan() {
        game.getGamers().sort(new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                if (o1.getBatmaSayisi() > o2.getBatmaSayisi()) {
                    return 1;
                } else if (o1.getBatmaSayisi() < o2.getBatmaSayisi()) {
                    return -1;
                } else
                    return 0;
            }
        });
        return game.getGamers().get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void kimkazandi() {
        game.getGamers().sort(new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                if (o1.getAlinanKartlar().get(game.getNowPlaying()) > o2.getAlinanKartlar().get(game.getNowPlaying()))
                    return -1;
                else if (o1.getAlinanKartlar().get(game.getNowPlaying()) < o2.getAlinanKartlar().get(game.getNowPlaying()))
                    return 1;
                else return 0;
            }
        });
        int puanlar[] = new int[4];
        for (int i = 0; i < game.oyuncuSayisi(); i++) {
            puanlar[i] = game.getGamers().get(i).getAlinanKartlar().get(game.getNowPlaying());
        }
        if (puanlar[0] == puanlar[1]) {
            game.getGamers().get(1).kazandi();
            if (puanlar[0] == puanlar[2]) {
                game.getGamers().get(2).kazandi();
                if (game.oyuncuSayisi() == 4) {
                    if (puanlar[0] == puanlar[3]) {
                        game.getGamers().get(3).kazandi();
                    }
                }
            }
        } else game.getGamers().get(0).kazandi();
        sirala();
    }
}
