package com.github.mateuszhorczak;
import java.util.ArrayList;
import java.util.Objects;

public class Centrum {
    private ArrayList<Wpis> archiwum;
    private ArrayList<KlientCentrum> klienciCentrum;
    private ArrayList<Bank> banki;

    public Centrum() {
        archiwum = new ArrayList<>();
        klienciCentrum = new ArrayList<>();
        banki = new ArrayList<>();
    }

    public void dodajBank(Bank bank) {
        banki.add(bank);
    }

    public void wypiszBanki() {
        for (var item : banki) {
            System.out.println(item.pobierzNazwe());
        }
    }

    public void dodajWpis(Wpis wpis) {
        archiwum.add(wpis);
    }

    public void wypiszWpisy() {
        for (var item : archiwum) {
            if(item.getOsoba() != null) {
                System.out.println("Imie: " + item.getOsoba().getImie());
                System.out.println("Nazwisko: " + item.getOsoba().getNazwisko());
            }
            System.out.println("Data: " + item.getData());
            System.out.println("Kwota: " + item.getKwota());
            if(item.getKarta() != null) {
                System.out.println("Numer Karty: " + item.getKarta().getNumerKarty());
                if (item.getKarta() instanceof KartaKredytowa) {
                    System.out.println("Rodzaj Karty: Karta Kredytowa");
                }
                if (item.getKarta() instanceof KartaDebetowa) {
                    System.out.println("Rodzaj Karty: Karta Debetowa");
                }
            }
            if(item.getKlientCentrum() !=null) {
                if (item.getKlientCentrum() instanceof Sklep) {
                    System.out.println("Rodzaj Firmy: Sklep");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwa_Firmy());
                }
                if (item.getKlientCentrum() instanceof FirmaTransportowa) {
                    System.out.println("Rodzaj Firmy: Firma Transportowa");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwa_Firmy());
                }
                if (item.getKlientCentrum() instanceof ZakladUslugowy) {
                    System.out.println("Rodzaj Firmy: Zaklad Uslugowy");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwa_Firmy());
                }
            }
            if (item.getBank() != null) {
                System.out.println("Nazwa banku: " + item.getBank().pobierzNazwe());
            }
            System.out.println("Powodzenie transakcji: " + item.getPowodzenieTranskacji());
            System.out.println("****************************************");
        }

    }

    public String pobierzWpis(Wpis wpis) {
        String imie = null,nazwisko = null,data,kwota,numerKarty = null,rodzajKarty =null,rodzajFirmy = null,nazwaFirmy = null,nazwaBanku = null,powodzenieTransakcji;
            if(wpis.getOsoba() != null) {
                imie = "Imie: " + wpis.getOsoba().getImie();
                nazwisko = "Nazwisko: " + wpis.getOsoba().getNazwisko();
            }
            data = "Data: " + wpis.getData();
            kwota = "Kwota: " + wpis.getKwota();
            if(wpis.getKarta() != null) {
                numerKarty = "Numer Karty: " + wpis.getKarta().getNumerKarty();
                if (wpis.getKarta() instanceof KartaKredytowa) {
                    rodzajKarty = "Rodzaj Karty: Karta Kredytowa";
                }
                if (wpis.getKarta() instanceof KartaDebetowa) {
                    rodzajKarty = "Rodzaj Karty: Karta Debetowa";
                }
            }
            if(wpis.getKlientCentrum() !=null) {
                if (wpis.getKlientCentrum() instanceof Sklep) {
                    rodzajFirmy = "Rodzaj Firmy: Sklep";
                    nazwaFirmy = "Nazwa Firmy: " + wpis.getKlientCentrum().getNazwa_Firmy();
                }
                if (wpis.getKlientCentrum() instanceof FirmaTransportowa) {
                    rodzajFirmy = "Rodzaj Firmy: Firma Transportowa";
                    nazwaBanku = "Nazwa Firmy: " + wpis.getKlientCentrum().getNazwa_Firmy();
                }
                if (wpis.getKlientCentrum() instanceof ZakladUslugowy) {
                    rodzajFirmy = "Rodzaj Firmy: Zaklad Uslugowy";
                    nazwaBanku = "Nazwa Firmy: " + wpis.getKlientCentrum().getNazwa_Firmy();
                }
            }
            if (wpis.getBank() != null) {
                nazwaBanku = "Nazwa banku: " + wpis.getBank().pobierzNazwe();
            }
            powodzenieTransakcji = "Powodzenie transakcji: " + wpis.getPowodzenieTranskacji();
            if (imie == null && nazwisko == null && numerKarty == null && rodzajKarty ==null && nazwaBanku == null){
                return (data + kwota + rodzajFirmy + nazwaFirmy + powodzenieTransakcji);
            }
            else {
                return (imie + nazwisko + data + kwota + numerKarty + rodzajKarty + rodzajFirmy + nazwaFirmy +nazwaBanku+powodzenieTransakcji);
            }

    }

    public boolean czyIstniejeKlient(String nazwaFirmy) {
        for (var item : klienciCentrum) {
            if (Objects.equals(item.getNazwa_Firmy(), nazwaFirmy)) {
                return true;
            }
        }
        return false;
    }

    public boolean dodajKlient(KlientCentrum klient) {
        if (!czyIstniejeKlient(klient.Nazwa_Firmy)) {
            klienciCentrum.add(klient);
            return true;
        }
        return false;
    }

    public boolean usunKlient(KlientCentrum klient) {
        if (czyIstniejeKlient(klient.Nazwa_Firmy)) {
            klienciCentrum.remove(klient);
            return true;
        }
        return false;
    }

    public void wypiszKlientow() {
        for (var item : klienciCentrum) {
            System.out.println(item.getNazwa_Firmy());
        }
    }

    public boolean znajdzNumerKarty(int numerKarty) {
        for (var item : banki) {
            for (var item1 : item.getOsoby()) {
                for (var item2 : item1.getKarty()) {
                    if (numerKarty == item2.getNumerKarty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Karta znajdzKarte(int numerKarty) {
        for (var item : banki) {
            for (var item1 : item.getOsoby()) {
                for (var item2 : item1.getKarty()) {
                    if (numerKarty == item2.getNumerKarty()) {
                        return item2;
                    }
                }
            }
        }
        return null;
    }

    public Osoba znajdzOsobe(int numerKarty) {
        for (var item : banki) {
            for (var item1 : item.getOsoby()) {
                for (var item2 : item1.getKarty()) {
                    if (numerKarty == item2.getNumerKarty()) {
                        return item1;
                    }
                }
            }
        }
        return null;
    }

    public Bank znajdzBank(int numerKarty) {
        for (var item : banki) {
            for (var item1 : item.getOsoby()) {
                for (var item2 : item1.getKarty()) {
                    if (numerKarty == item2.getNumerKarty()) {
                        return item;
                    }
                }
            }
        }
        return null;
    }

    public boolean wyslijZapytanie(int numerKarty, double kwota){
        return true;
    }

    public int iloscBankow(Centrum centrum) {
        return centrum.getBanki().lastIndexOf(centrum.getBanki().get(0));
    }

    public int iloscSklepow(Centrum centrum) {
        return centrum.getKlienciCentrum().lastIndexOf(centrum.getKlienciCentrum().get(0));
    }

    public boolean czyNumerKartyJestZajety(int numerKarty) {
        for (var item : getBanki()) {
            for (var item2 : item.getOsoby()) {
                for (var item3 : item2.getKarty()) {
                    if (item3.getNumerKarty() == numerKarty) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Wpis> getArchiwum() {
        return archiwum;
    }

    public ArrayList<KlientCentrum> getKlienciCentrum() {
        return klienciCentrum;
    }

    public ArrayList<Bank> getBanki() {
        return banki;
    }
}