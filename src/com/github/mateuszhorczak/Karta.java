package com.github.mateuszhorczak;

public class Karta {
    int numerKarty;
    float stanKarty;
    String rodzajKarty;

    public Karta(int numerKarty, float stanKarty, String rodzajKarty) {
        this.numerKarty = numerKarty;
        this.stanKarty = stanKarty;
        this.rodzajKarty = rodzajKarty;
    }

    public Karta(Karta karta) {
        numerKarty = karta.numerKarty;
        stanKarty = karta.numerKarty;
        rodzajKarty = karta.rodzajKarty;
    }

    public Karta() {

    }

    public int getNumerKarty() {
        return numerKarty;
    }

    public float getStanKarty() {
        return stanKarty;
    }

    public void setStanKarty(float stanKarty) {
        this.stanKarty = stanKarty;
    }

    public String getRodzajKarty() {
        return rodzajKarty;
    }
}
