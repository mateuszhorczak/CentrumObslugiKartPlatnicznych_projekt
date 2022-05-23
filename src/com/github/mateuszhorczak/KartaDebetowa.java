package com.github.mateuszhorczak;

public class KartaDebetowa extends Karta {
    private double rozmiarDebetu;

    public KartaDebetowa(int numerKarty, double stanKarty, double rozmiarDebetu) {
        super(numerKarty, stanKarty);
        this.rozmiarDebetu = rozmiarDebetu;
    }
}