package com.github.mateuszhorczak;

import java.util.Date;

public class Wpis {
    private String imie, nazwisko, usluga, firma, bank;
    private Date data;
    float kwota;
    int numerKarty;
    boolean powodzenie;

    public Wpis() {

    }

    public Wpis(String imie, String nazwisko, String usluga, String firma, String bank, Date data, float kwota, int numerKarty, boolean powodzenie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.usluga = usluga;
        this.firma = firma;
        this.bank = bank;
        this.data = data;
        this.kwota = kwota;
        this.numerKarty = numerKarty;
        this.powodzenie = powodzenie;
    }

    public Wpis(Wpis wpis) {
        imie = wpis.imie;
        nazwisko = wpis.nazwisko;
        usluga = wpis.usluga;
        firma = wpis.firma;
        bank = wpis.bank;
        data = wpis.data;
        kwota = wpis.kwota;
        numerKarty = wpis.numerKarty;
        powodzenie = wpis.powodzenie;
    }


}
