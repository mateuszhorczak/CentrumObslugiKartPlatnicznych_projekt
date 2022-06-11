package com.github.mateuszhorczak;

import java.util.ArrayList;
import java.util.Objects;

//elo
public class Centrum {
    private final ArrayList<Wpis> archiwum;
    private final ArrayList<KlientCentrum> klienciCentrum;
    private final ArrayList<Bank> banki;

    public Centrum() {
        archiwum = new ArrayList<>();
        klienciCentrum = new ArrayList<>();
        banki = new ArrayList<>();
    }

    public void dodajBank(Bank bank) {
        banki.add(bank);
    }

    public void wypiszBanki() {
        int i = 0;
        for (var item : banki) {
            System.out.println(item.pobierzNazwe() + " index: " + i++);
        }
    }

    public void dodajWpis(Wpis wpis) {
        archiwum.add(wpis);
    }

    public void wypiszWpisy() {
        for (var item : archiwum) {
            if (item.getOsoba() != null) {
                System.out.println("Imie: " + item.getOsoba().getImie());
                System.out.println("Nazwisko: " + item.getOsoba().getNazwisko());
            }
            System.out.println("Data: " + item.getData());
            System.out.println("Kwota: " + item.getKwota());
            if (item.getKarta() != null) {
                System.out.println("Numer Karty: " + item.getKarta().getNumerKarty());
                if (item.getKarta() instanceof KartaKredytowa) {
                    System.out.println("Rodzaj Karty: Karta Kredytowa");
                }
                if (item.getKarta() instanceof KartaDebetowa) {
                    System.out.println("Rodzaj Karty: Karta Debetowa");
                }
                if (item.getKarta() instanceof KartaBankomatowa) {
                    System.out.println("Rodzaj Karty: Karta Bankomatowa");
                }
            }
            if (item.getKlientCentrum() != null) {
                if (item.getKlientCentrum() instanceof Sklep) {
                    System.out.println("Rodzaj Firmy: Sklep");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwaFirmy());
                }
                if (item.getKlientCentrum() instanceof FirmaTransportowa) {
                    System.out.println("Rodzaj Firmy: Firma Transportowa");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwaFirmy());
                }
                if (item.getKlientCentrum() instanceof ZakladUslugowy) {
                    System.out.println("Rodzaj Firmy: Zaklad Uslugowy");
                    System.out.println("Nazwa Firmy: " + item.getKlientCentrum().getNazwaFirmy());
                }
            }
            if (item.getBank() != null) {
                System.out.println("Nazwa banku: " + item.getBank().pobierzNazwe());
            }
            System.out.println("Powodzenie transakcji: " + item.getPowodzenieTranskacji());
            System.out.println("******************** ********************");
        }

    }

    public String pobierzWpis(Wpis wpis) {
        String imie = null, nazwisko = null, data, kwota, numerKarty = null, rodzajKarty = null,
                rodzajFirmy = null, nazwaFirmy = null, nazwaBanku = null, powodzenieTransakcji;
        String typOperacji;

        if (wpis.getOsoba() != null) {
            imie = "Imie: " + wpis.getOsoba().getImie();
            nazwisko = "Nazwisko: " + wpis.getOsoba().getNazwisko();
        }
        data = "Data: " + wpis.getData();
        kwota = "Kwota: " + wpis.getKwota();
        if (wpis.getKarta() != null) {
            numerKarty = "Numer Karty: " + wpis.getKarta().getNumerKarty();
            if (wpis.getKarta() instanceof KartaKredytowa) {
                rodzajKarty = "Rodzaj Karty: Karta Kredytowa";
            }
            if (wpis.getKarta() instanceof KartaDebetowa) {
                rodzajKarty = "Rodzaj Karty: Karta Debetowa";
            }
            if (wpis.getKarta() instanceof KartaBankomatowa) {
                rodzajKarty = "Rodzaj Karty: Karta Bankomatowa";
            }
        }
        if (wpis.getKlientCentrum() != null) {

            if (wpis.getKlientCentrum() instanceof Sklep) {
                rodzajFirmy = "Rodzaj Firmy: Sklep";
            }
            if (wpis.getKlientCentrum() instanceof FirmaTransportowa) {
                rodzajFirmy = "Rodzaj Firmy: Firma Transportowa";
            }
            if (wpis.getKlientCentrum() instanceof ZakladUslugowy) {
                rodzajFirmy = "Rodzaj Firmy: Zaklad Uslugowy";
            }
            nazwaFirmy = "Nazwa Firmy: " + wpis.getKlientCentrum().getNazwaFirmy();
        }
        if (wpis.getBank() != null) {
            nazwaBanku = "Nazwa banku: " + wpis.getBank().pobierzNazwe();
        }
        powodzenieTransakcji = "Powodzenie transakcji: " + wpis.getPowodzenieTranskacji();

       /* rodzajFirmy = rodzajFirmy == null ? "" : rodzajFirmy + "\n";
        nazwaFirmy = nazwaFirmy == null ? "" : nazwaFirmy + "\n";
        typOperacji = wpis.typOperacji == null ? "" : "Typ operacji: " + wpis.typOperacji + "\n";*/

        return (imie + "\n" + nazwisko + "\n" + data + "\n" + kwota + "\n" + numerKarty + "\n" + rodzajKarty + "\n" +
                rodzajFirmy + nazwaFirmy /*+ typOperacji*/ + nazwaBanku + "\n" + powodzenieTransakcji + "\n"
                + "******************** ********************\n");

    }

    public boolean czyIstniejeKlient(String nazwaFirmy) {
        for (var item : klienciCentrum) {
            if (Objects.equals(item.getNazwaFirmy(), nazwaFirmy)) {
                return true;
            }
        }
        return false;
    }

    public boolean dodajKlient(KlientCentrum klient) {
        if (!czyIstniejeKlient(klient.nazwaFirmy)) {
            klienciCentrum.add(klient);
            return true;
        }
        return false;
    }

    public boolean usunKlient(KlientCentrum klient) {
        if (czyIstniejeKlient(klient.nazwaFirmy)) {
            klienciCentrum.remove(klient);
            return true;
        }
        return false;
    }

    public void wypiszKlientow() {
        for (var item : klienciCentrum) {
            System.out.println(item.getNazwaFirmy());
        }
    }

    public boolean czyIstniejeNumerKarty(int numerKarty) {
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

    public Osoba znajdzOsobePoNumerze(int numerKarty) {
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

    public Osoba znajdzOsobePoImieniuINazwisku(String imie, String nazwisko) {
        for (var item : banki) {
            for (var item1 : item.getOsoby()) {
                if (Objects.equals(item1.getImie(), imie) && Objects.equals(item1.getNazwisko(), nazwisko)) {
                    return item1;
                }
            }
        }
        return null;
    }

    public Bank znajdzBankWKtorymJestTenNumerKarty(int numerKarty) {
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

    public boolean wyslijZapytanie(int numerKarty, double kwota) {
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