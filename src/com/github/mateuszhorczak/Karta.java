package com.github.mateuszhorczak;

public abstract class Karta {
    protected int numerKarty;
    protected double stanKarty;

    public Karta(int numerKarty, double stanKarty) {
        this.numerKarty = numerKarty;
        this.stanKarty = stanKarty;
    }

    public int getNumerKarty() {
        return numerKarty;
    }

    public double getStanKarty() {
        return stanKarty;
    }

    public void setStanKarty(double stanKarty) {
        this.stanKarty = stanKarty;
    }
}
