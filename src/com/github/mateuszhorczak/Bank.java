package com.github.mateuszhorczak;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private ArrayList<Osoba> osoby;
    private String nazwaBanku;
    private Karta tempKarta;

    public Bank(String nazwaBanku) {
        this.nazwaBanku = nazwaBanku;
        this.osoby = new ArrayList<>();
    }

    public void  wczytajDane(String nazwaPliku) throws IOException {
        File plik = new File(nazwaPliku);
        Scanner scannerZPliku = new Scanner(plik);
        while (scannerZPliku.hasNext()) {
            String linia = scannerZPliku.nextLine();
            String[] wyrazy = linia.split(" ");
            int rodzajKarty = Integer.parseInt(wyrazy[4]);
            int numerKarty = Integer.parseInt(wyrazy[2]);
            double stanKarty = Double.parseDouble(wyrazy[3]);
            boolean x =false;
            for (int i = 0; i < osoby.size(); i++) {
                String imie = osoby.get(i).getImie();
                String nazwisko = osoby.get(i).getNazwisko();
                if (imie.equals(wyrazy[0])  && nazwisko.equals(wyrazy[1])) {
                    x = true;
                    switch (rodzajKarty) {
                        case 1:
                            KartaBankomatowa kartaBankomatowa = new KartaBankomatowa(numerKarty, stanKarty);
                            osoby.get(i).dodajKarte(kartaBankomatowa);
                            break;
                        case 2:
                            KartaDebetowa kartaDebetowa = new KartaDebetowa(numerKarty, stanKarty);
                            osoby.get(i).dodajKarte(kartaDebetowa);
                            break;
                        case 3:
                            KartaKredytowa kartaKredytowa = new KartaKredytowa(numerKarty, stanKarty);
                            osoby.get(i).dodajKarte(kartaKredytowa);
                            break;
                    }
                }
            }
            if (x == false) {
                Osoba nowaOsoba = new Osoba(wyrazy[0], wyrazy[1]);
                switch (rodzajKarty) {
                    case 1:
                        KartaBankomatowa kartaBankomatowa = new KartaBankomatowa(numerKarty, stanKarty);
                        nowaOsoba.dodajKarte(kartaBankomatowa);
                        break;
                    case 2:
                        KartaDebetowa kartaDebetowa = new KartaDebetowa(numerKarty, stanKarty);
                        nowaOsoba.dodajKarte(kartaDebetowa);
                        break;
                    case 3:
                        KartaKredytowa kartaKredytowa = new KartaKredytowa(numerKarty, stanKarty);
                        nowaOsoba.dodajKarte(kartaKredytowa);
                        break;
                }
                osoby.add(nowaOsoba);
            }
        }
    }

    public void zapiszDane(String nazwaPliku) throws IOException {
        int i;
        File plik = new File(nazwaPliku);
        plik.createNewFile();
        PrintWriter zapiszDoPliku = new PrintWriter(plik);
        for(Osoba osoba: osoby){
            for(Karta karta: osoba.getKarty()) {
                zapiszDoPliku.printf(osoba.getImie() + " ");
                zapiszDoPliku.printf(osoba.getNazwisko() + " ");
                zapiszDoPliku.printf(karta.getNumerKarty() + " ");
                zapiszDoPliku.printf(karta.getStanKarty() + " ");
                if(karta instanceof KartaBankomatowa){
                    zapiszDoPliku.printf("1");
                }
                if(karta instanceof KartaDebetowa){
                    zapiszDoPliku.printf("2");
                }
                if(karta instanceof KartaKredytowa){
                    zapiszDoPliku.printf("3");
                }
                zapiszDoPliku.printf("\n");
            }
        }
        zapiszDoPliku.close();
    }

    public String pobierzNazwe(){
        return nazwaBanku;
    }

    public boolean czyNalezyOsobaDoBanku(int numerPodany) {
        for (var item : osoby) {
            for (var item2 : item.getKarty()) {
                if (item2.getNumerKarty() == numerPodany) {
                    tempKarta = item2;
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
            tempKarta.setStanKarty(tempKarta.getStanKarty() + kwota);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean wyplac(int numerPodany, double kwota) {
        if (czyNalezyOsobaDoBanku(numerPodany)) {
            tempKarta.setStanKarty(tempKarta.getStanKarty() - kwota);
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Osoba> getOsoby() {
        return osoby;
    }

    public void dodajOsobe(Osoba osoba){
        osoby.add(osoba);
    }
}
