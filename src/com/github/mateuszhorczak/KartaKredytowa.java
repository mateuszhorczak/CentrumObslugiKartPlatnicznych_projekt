package com.github.mateuszhorczak;

public class KartaKredytowa extends Karta {
    private double limitKretydowy;

    public KartaKredytowa(int numerKarty, double stanKarty, double limitKretydowy) {
        super(numerKarty, stanKarty);
        this.limitKretydowy = limitKretydowy;
    }
}
