package com.github.mateuszhorczak;

import java.util.Date;

public class Wpis {
    Osoba osoba;
    Date data;
    double kwota;
    Karta karta;
    KlientCentrum klientCentrum;
    Bank bank;
    boolean powodzenie_transkacji;

    public Wpis(Osoba osoba, Date data, double kwota, Karta karta, KlientCentrum klientCentrum,
                Bank bank, boolean powodzenie_transakcji){
        this.osoba = osoba;
        this.data = data;
        this.kwota = kwota;
        this.karta = karta;
        this.klientCentrum = klientCentrum;
        this.bank = bank;
        this.powodzenie_transkacji = powodzenie_transakcji;
    }
}
