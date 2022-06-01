package com.github.mateuszhorczak;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Centrum {
    private ArrayList<Wpis> archiwum;
    private ArrayList<KlientCentrum> klienciCentrum;
    private ArrayList<Bank> banki;

    public void dodajWpis(Wpis wpis){
        archiwum.add(wpis);
    }

    public void usunWpis(Wpis wpis){
        archiwum.remove(wpis);
    }

    public boolean wyslijZapytanie(int numerKarty, double kwota){
        return true;
    }

    public boolean czyIstniejeKlient(String nazwaFirmy) {
        for (var item: klienciCentrum) {
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

    public int iloscBankow(Centrum centrum) {
        return centrum.getBanki().lastIndexOf(centrum.getBanki().get(0));
    }

    public int iloscSklepow(Centrum centrum) {
        return centrum.getKlienciCentrum().lastIndexOf(centrum.getKlienciCentrum().get(0));
    }

    public void wypiszBanki() {
        for (var item : banki) {
            System.out.println(item);
        }
    }

    public void wypiszKlientowCentrum() {
        for (var item : getKlienciCentrum()) {
            System.out.println(item.Nazwa_Firmy);
        }
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

    public List<KlientCentrum> getKlienciCentrum() {
        return klienciCentrum;
    }

    public List<Bank> getBanki() {
        return banki;
    }
}