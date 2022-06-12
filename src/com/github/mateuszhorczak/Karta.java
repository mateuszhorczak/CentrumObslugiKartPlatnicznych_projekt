package com.github.mateuszhorczak;
public abstract class Karta {
    protected int numerKarty;
    protected double stanKarty;

    public Karta(int numerKarty, double stanKarty) {
        this.numerKarty = numerKarty;
        this.stanKarty = stanKarty;
    }
    public Karta(int numerKarty) {
        this.numerKarty = numerKarty;
    }

    public int getNumerKarty() {
        return numerKarty;
    }

    public double getStanKarty() {
        return stanKarty;
    }

    public boolean platnosc(double kwota){
        if (stanKarty >= kwota) {
            stanKarty -= kwota;
            return true;
        }
        else{
            System.out.println("Za malo srodkow - platnosc nie zrealizowana!");
            return false;
        }
    }

    public void setStanKarty(double stanKarty) {
        this.stanKarty = stanKarty;
    }
}
