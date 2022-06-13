package com.github.mateuszhorczak;

public class KartaDebetowa extends Karta {

    public KartaDebetowa(int numerKarty, double stanKarty) {
        super(numerKarty, stanKarty);
        double rozmiarDebetu = 2000;
        this.stanKarty += rozmiarDebetu;
    }

    public KartaDebetowa(int numerKarty) {
        super(numerKarty);
    }
}
