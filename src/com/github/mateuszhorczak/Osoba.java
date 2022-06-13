package com.github.mateuszhorczak;

import java.util.ArrayList;

public class Osoba {
    private final String imie;
    private final String nazwisko;
    private final ArrayList<Karta> karty;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.karty = new ArrayList<>();
    }

    public void dodajKarte(Karta karta) {
        if (!czyNalezyKartaDoOsoby(karta)) {
            karty.add(karta);
        }
    }

    public boolean czyNalezyKartaDoOsoby(Karta karta) {
        for (var item : karty) {
            if (item.equals(karta)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Karta> getKarty() {
        return karty;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
