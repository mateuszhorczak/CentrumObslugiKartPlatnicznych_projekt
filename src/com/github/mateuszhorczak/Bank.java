package com.github.mateuszhorczak;

import java.util.List;

public class Bank {
    private List<Karta> karty;

    public void wczytajDane() {
        //TODO Wczytywanie danych z pliku
    }

    public void dodajKarte(Karta karta) {
        karty.add(karta);
    }

    public void usunKarte(Karta karta) {
        karty.remove(karta);
    }

    public boolean czyNalezy(Karta karta) {
        return karty.contains(karta); //TODO lepiej zeby przekazywalo zamiast obiektu Karta sam numer tej karty (tak na diagramie jest)
        //nie mam pomyslu jak to zrobic
    }

    public void wplac(Karta karta, float kwota) {
        karta.setStanKarty(karta.getStanKarty() + kwota); //TODO tak samo jak wyzej
    }

    public void wyplac(Karta karta, float kwota) {
        karta.setStanKarty(karta.getStanKarty() - kwota);//TODO tak samo jak jeszcze wyzej
    }
}
