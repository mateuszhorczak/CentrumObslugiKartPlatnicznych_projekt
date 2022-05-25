package com.github.mateuszhorczak;

import java.util.List;

public class Bank {
    private List<Osoba> osoby;
    private Karta temp;

    public boolean wczytajDane() { //TODO jak to sie wczytuje many
        return true;
    }

    public boolean czyNalezyOsobaDoBanku(int numerPodany) {
        for (var item : osoby) {
            for (var item2 : item.getKarty()) {
                if (item2.getNumerKarty() == numerPodany) {
                    temp = item2;
                    return true;
                }
            }
        }
        return false;
    }

    public Osoba znajdzOsobe(String imie, String nazwisko) {
        for (var item : osoby) {
            if (imie.equalsIgnoreCase(item.getImie()) && nazwisko.equalsIgnoreCase(item.getNazwisko())) {
                return item;
            }
        }
        return null;
    }


    public boolean wplac(int numerPodany, double kwota) {
        if (czyNalezyOsobaDoBanku(numerPodany)) {
            temp.setStanKarty(temp.getStanKarty() + kwota);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean wyplac(int numerPodany, double kwota) {
        if (czyNalezyOsobaDoBanku(numerPodany)) {
            temp.setStanKarty(temp.getStanKarty() - kwota);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }
}
