package com.github.mateuszhorczak;

public abstract class KlientCentrum {
    String Nazwa_Firmy;

    public KlientCentrum(String Nazwa_Firmy) {
        this.Nazwa_Firmy = Nazwa_Firmy;
    }
    public String getNazwa_Firmy(){
        return Nazwa_Firmy;
    }
}
