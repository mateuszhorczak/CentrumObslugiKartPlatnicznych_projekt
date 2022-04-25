package com.github.mateuszhorczak;

import java.util.List;

public class Centrum {
    private List<Wpis> archwium;

    public void  dodajWpis(Wpis wpis) {
        archwium.add(wpis);
    }

    public void usunWpis(Wpis wpis) {
        archwium.remove(wpis);
    }

    public boolean wyslijZapytanie(Karta karta, float kwota) { //TODO zamiast karty chyba lepiej bedzie przekazywac sam numer karty
        return true;
    }

}
