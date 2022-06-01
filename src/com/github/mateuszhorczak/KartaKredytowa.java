package com.github.mateuszhorczak;
public class KartaKredytowa extends Karta {

    private double limitKretydowy;

    public KartaKredytowa(int numerKarty, double stanKarty) {
        super(numerKarty, stanKarty);      //Ten stan karty to jest w tym wypadku Limit Kredytowy!
    }
}
