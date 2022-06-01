package com.github.mateuszhorczak;
import java.util.ArrayList;

public class Osoba {
    private String imie, nazwisko;
    private ArrayList<Karta> karty;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.karty = new ArrayList<>();
    }

    public Osoba() {

    }

    public int zliczKarty() {
        int i = 0;
        for (var item : karty) {
            i++;
        }
        return i;
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
