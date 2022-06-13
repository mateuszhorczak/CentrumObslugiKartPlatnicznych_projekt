package com.github.mateuszhorczak;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private final ArrayList<Osoba> osoby;
    private final String nazwaBanku;

    public Bank(String nazwaBanku) {
        this.nazwaBanku = nazwaBanku;
        this.osoby = new ArrayList<>();
    }

    public void wczytajDane(String nazwaPliku) throws IOException {
        File plik = new File(nazwaPliku);
        Scanner scannerZPliku = new Scanner(plik);
        while (scannerZPliku.hasNext()) {
            String linia = scannerZPliku.nextLine();
            String[] wyrazy = linia.split(" ");
            int rodzajKarty = Integer.parseInt(wyrazy[4]);
            int numerKarty = Integer.parseInt(wyrazy[2]);
            double stanKarty = Double.parseDouble(wyrazy[3]);
            boolean x = false;
            for (Osoba osoba : osoby) {
                String imie = osoba.getImie();
                String nazwisko = osoba.getNazwisko();
                if (imie.equals(wyrazy[0]) && nazwisko.equals(wyrazy[1])) {
                    x = true;
                    dodajDobryTypKarty(rodzajKarty, numerKarty, stanKarty, osoba);
                }
            }
            if (!x) {
                Osoba nowaOsoba = new Osoba(wyrazy[0], wyrazy[1]);
                dodajDobryTypKarty(rodzajKarty, numerKarty, stanKarty, nowaOsoba);
                osoby.add(nowaOsoba);
            }
        }
    }

    public void dodajDobryTypKarty(int rodzajKarty, int numerKarty, double stanKarty, Osoba osoba) {
        switch (rodzajKarty) {
            case 1:
                KartaBankomatowa kartaBankomatowa = new KartaBankomatowa(numerKarty, stanKarty);
                osoba.dodajKarte(kartaBankomatowa);
                break;
            case 2:
                KartaDebetowa kartaDebetowa = new KartaDebetowa(numerKarty, stanKarty);
                osoba.dodajKarte(kartaDebetowa);
                break;
            case 3:
                KartaKredytowa kartaKredytowa = new KartaKredytowa(numerKarty, stanKarty);
                osoba.dodajKarte(kartaKredytowa);
                break;
        }
    }

    public void zapiszDane(String nazwaPliku) throws IOException {
        File plik = new File(nazwaPliku);
        plik.createNewFile();
        PrintWriter zapiszDoPliku = new PrintWriter(plik);
        for (Osoba osoba : osoby) {
            for (Karta karta : osoba.getKarty()) {
                zapiszDoPliku.printf(osoba.getImie() + " ");
                zapiszDoPliku.printf(osoba.getNazwisko() + " ");
                zapiszDoPliku.printf(karta.getNumerKarty() + " ");
                zapiszDoPliku.printf(karta.getStanKarty() + " ");
                if (karta instanceof KartaBankomatowa) {
                    zapiszDoPliku.printf("1");
                }
                if (karta instanceof KartaDebetowa) {
                    zapiszDoPliku.printf("2");
                }
                if (karta instanceof KartaKredytowa) {
                    zapiszDoPliku.printf("3");
                }
                zapiszDoPliku.printf("\n");
            }
        }
        zapiszDoPliku.close();
    }

    public String pobierzNazwe() {
        return nazwaBanku;
    }

    public boolean czyNalezyOsobaDoBanku(int numerPodany) {
        for (var item : osoby) {
            for (var item2 : item.getKarty()) {
                if (item2.getNumerKarty() == numerPodany) {
                    return true;
                }
            }
        }
        return false;
    }

    public void wplac(int numerPodany, double kwota) throws CardNotFoundException {
        Karta karta = wezKarte(numerPodany);

        if (czyNalezyOsobaDoBanku(numerPodany)) {
            karta.setStanKarty(karta.getStanKarty() + kwota);
        }

    }

    public void wyplac(int numerPodany, double kwota) throws CardNotFoundException, InvalidCardDataException {
        Karta karta = wezKarte(numerPodany);

        if (karta.getStanKarty() - kwota < 0) {
            throw new InvalidCardDataException();
        }

        if (czyNalezyOsobaDoBanku(numerPodany)) {
            karta.setStanKarty(karta.getStanKarty() - kwota);
        }
    }

    private Karta wezKarte(int numerPodany) throws CardNotFoundException {
        for (var osoba : osoby) {
            for (var karta : osoba.getKarty()) {
                if (karta.getNumerKarty() == numerPodany) {
                    return karta;
                }
            }
        }

        throw new CardNotFoundException();
    }

    public ArrayList<Osoba> getOsoby() {
        return osoby;
    }

    public void dodajOsobe(Osoba osoba) {
        if (osoby.contains(osoba)) {
            return;
        }
        osoby.add(osoba);
    }
}
