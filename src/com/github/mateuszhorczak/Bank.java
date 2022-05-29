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
                for (Osoba osoba: osoby) {
                    if (Objects.equals(osoba.getImie(), wyrazy[0]) && Objects.equals(osoba.getNazwisko(), wyrazy[1])){
                        osoba.dodajKarte(kartaBankomatowa);
                    }
                    else{
                        ArrayList<Karta> karty = new ArrayList<>();
                        karty.add(kartaBankomatowa);
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],karty);
                        osoby.add(osoba1);
                    }
                }
            }
            if (rodzajKarty == 2){
                KartaDebetowa kartaDebetowa = new KartaDebetowa(numerKarty, stanKarty);
                for (Osoba osoba : osoby){
                    if (Objects.equals(osoba.getImie(), wyrazy[0]) && Objects.equals(osoba.getNazwisko(), wyrazy[1])){
                        osoba.dodajKarte(kartaDebetowa);
                    }
                    else{
                        ArrayList<Karta> karty = new ArrayList<>();
                        karty.add(kartaDebetowa);
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],karty);
                        osoby.add(osoba1);
                    }
                }
            }
            if (rodzajKarty == 3){
                KartaKredytowa kartaKredytowa = new KartaKredytowa(numerKarty,stanKarty);
                for (Osoba osoba: osoby){
                    if (Objects.equals(osoba.getImie(), wyrazy[0]) && Objects.equals(osoba.getNazwisko(), wyrazy[1])){
                        osoba.dodajKarte(kartaKredytowa);
                    }
                    else{
                        ArrayList<Karta> karty = new ArrayList<>();
                        karty.add(kartaKredytowa);
                        Osoba osoba1 = new Osoba(wyrazy[0],wyrazy[1],karty);
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

    public void dodajOsobe(Osoba osoba){
        osoby.add(osoba);
    }
}
