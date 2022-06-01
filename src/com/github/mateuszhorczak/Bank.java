package com.github.mateuszhorczak;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bank {
    private List<Osoba> osoby;
    private Karta tempKarta;

    public Bank(List<Osoba> osoby) {
        this.osoby = osoby;
    }

    public void  wczytajDane() throws IOException {
        File plik = new File("dane.txt");
        Scanner scannerZPliku = new Scanner(plik);
        while(scannerZPliku.hasNext()) {
            String linia = scannerZPliku.nextLine();
            String[] wyrazy = linia.split(" ");
            int rodzajKarty = Integer.parseInt(wyrazy[4]);
            int numerKarty = Integer.parseInt(wyrazy[2]);
            double stanKarty = Double.parseDouble(wyrazy[3]);

            Osoba osobaTest = new Osoba(); //Tworze testowa osobe i dodaje ja do listy
            osoby.add(osobaTest);          //dlatego ze nie wejdzie do foreacha w pustej liscie

            for (Osoba osoba : osoby) {
                if (!Objects.equals(osobaTest.getImie(), wyrazy[0]) && Objects.equals(osobaTest.getNazwisko(), wyrazy[1])) {
                    Osoba osoba1 = new Osoba(wyrazy[0], wyrazy[1], new ArrayList<>());
                    osoby.add(osoba1);
                }
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
        }
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

    public List<Osoba> getOsoby() {
        return osoby;
    }

    public void dodajOsobe(Osoba osoba){
        osoby.add(osoba);
    }
}
