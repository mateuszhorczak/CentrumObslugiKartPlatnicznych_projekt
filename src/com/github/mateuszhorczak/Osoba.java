package com.github.mateuszhorczak;

import java.util.List;

public class Osoba {
    private String imie, nazwisko;
    private List<Karta> karty;

    public Osoba(String imie, String nazwisko, List<Karta> karty) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.karty = karty;
    }

    public boolean usunKarte(Karta karta) {
        if (czyNalezyKartaDoOsoby(karta)) {
            karty.remove(karta);
            return true;
        }
        return false;
    }

    public boolean dodajKarte(Karta karta) {
        if (!czyNalezyKartaDoOsoby(karta)) {
            karty.add(karta);
            return true;
        }
        return false;
    }

    public boolean czyNalezyKartaDoOsoby(Karta karta) {
        for (var item : karty) {
            if (item.equals(karta)) {
                return true;
            }
        }
        return false;
    }

    public List<Karta> getKarty() {
        return karty;
    }

}