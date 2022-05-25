package com.github.mateuszhorczak;

import java.util.Date;

public class Wpis {
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
}
