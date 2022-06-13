package com.github.mateuszhorczak;

import java.util.Date;

public class Wpis {
    private final Osoba osoba;
    private final Date data;
    private final double kwota;
    private final Karta karta;
    private final KlientCentrum klientCentrum;
    private final Bank bank;
    private final boolean powodzenieTranskacji;

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
