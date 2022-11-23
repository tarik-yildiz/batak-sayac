package com.example.oyunsayac;

import java.util.List;

public class Round extends Game {
    public String ElSonuText;

    private List<Gamer> gamers;
    public Round() {
        ElSonuText = "12345";
    }
    public Round(List<Gamer> gamers, int maxRound) {
        super(gamers, maxRound);
    }



    public Round(List<Gamer> gamers) {
        super();
        this.gamers = gamers;
        ElSonuText="Henuz oynanmadı...";
    }

    public String toString() {

        return ElSonuText;

    }
    public void yazdir(){
        ElSonuText =
                gamers.get(0).getName() + " : "
                        + gamers.get(0).getAlinanKartlar().get(GameController.getInstance().getOyun().getNowPlaying()) + "\t\t" +
                                gamers.get(1).getName() + " : "
                        + gamers.get(1).getAlinanKartlar().get(GameController.getInstance().getOyun().getNowPlaying()) + "\t\t" +
                        gamers.get(2).getName() + " : "
                        + gamers.get(2).getAlinanKartlar().get(GameController.getInstance().getOyun().getNowPlaying()) + "\t\t";
        if (gamers.size() > 3) {
            ElSonuText += gamers.get(3).getName() + " : " + gamers.get(3).getAlinanKartlar().get(GameController.getInstance().getOyun().getNowPlaying()) + "\t";
        }
    }
}
