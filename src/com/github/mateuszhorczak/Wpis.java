package com.github.mateuszhorczak;

import java.util.Date;

public class Wpis {
    int idWpis;
    Osoba osoba;
    Date data;
    double kwota;
    Karta karta;
    KlientCentrum klientCentrum;
    Bank bank;
    boolean powodzenieTranskacji;

    public Wpis(Osoba osoba, Date data, double kwota, Karta karta, KlientCentrum klientCentrum,
                Bank bank, boolean powodzenieTransakcji) {
        this.osoba = osoba;
        this.data = data;
        this.kwota = kwota;
        this.karta = karta;
        this.klientCentrum = klientCentrum;
        this.bank = bank;
        this.powodzenieTranskacji = powodzenieTransakcji;
    }
    public Wpis(Date data, double kwota, KlientCentrum klientCentrum, boolean powodzenieTransakcji) {
        this.data = data;
        this.kwota = kwota;
        this.klientCentrum = klientCentrum;
        this.powodzenieTranskacji = powodzenieTransakcji;
    }

    public int getIdWpis() {
        return idWpis;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public Date getData() {
        return data;
    }

    public double getKwota() {
        return kwota;
    }

    public Karta getKarta() {
        return karta;
    }

    public KlientCentrum getKlientCentrum() {
        return klientCentrum;
    }

    public Bank getBank() {
        return bank;
    }

    public boolean getPowodzenieTranskacji() {
        return powodzenieTranskacji;
    }
}
