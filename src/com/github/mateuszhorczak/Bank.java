package com.github.mateuszhorczak;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private List<Osoba> osoby;
    private Karta tempKarta;

    public Bank(List<Osoba> osoby) {
        this.osoby = osoby;
    }

    public boolean wczytajDane() throws IOException {
        File plik = new File("dane.txt");
        Scanner scannerZPliku = new Scanner(plik);
        while(scannerZPliku.hasNext()){
            String linia = scannerZPliku.nextLine();
            String[] wyrazy = linia.split(" ");
            int rodzajKarty = Integer.parseInt(wyrazy[4]);
            int numerKarty = Integer.parseInt(wyrazy[2]);
            double stanKarty = Double.parseDouble(wyrazy[3]);
            if (rodzajKarty == 1){
                KartaBankomatowa kartaBankomatowa = new KartaBankomatowa(numerKarty, stanKarty);
                for (Osoba osoba: osoby){
                    if (osoba.getImie() == wyrazy[0] && osoba.getNazwisko() == wyrazy[1]){
                        osoba.dodajKarte(kartaBankomatowa);
                    }
                    else{
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],new ArrayList<>());
                        osoba1.dodajKarte(kartaBankomatowa);
                        osoby.add(osoba1);
                    }
                }
            }
            if (rodzajKarty == 2){
                KartaDebetowa kartaDebetowa = new KartaDebetowa(numerKarty, stanKarty);
                for (Osoba osoba: osoby){
                    if (osoba.getImie() == wyrazy[0] && osoba.getNazwisko() == wyrazy[1]){
                        osoba.dodajKarte(kartaDebetowa);
                    }
                    else{
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],new ArrayList<>());
                        osoba1.dodajKarte(kartaDebetowa);
                        osoby.add(osoba1);
                    }
                }
            }
            if (rodzajKarty == 3){
                KartaKredytowa kartaKredytowa = new KartaKredytowa(numerKarty,stanKarty);
                for (Osoba osoba: osoby){
                    if (osoba.getImie() == wyrazy[0] && osoba.getNazwisko() == wyrazy[1]){
                        osoba.dodajKarte(kartaKredytowa);
                    }
                    else{
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],new ArrayList<>());
                        osoba1.dodajKarte(kartaKredytowa);
                        osoby.add(osoba1);
                    }
                }
            }
        }
        return true;
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
}
