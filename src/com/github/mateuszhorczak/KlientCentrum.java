package com.github.mateuszhorczak;

public abstract class KlientCentrum {
    String Nazwa_Firmy;
    String Nazwa_Uslugi;

    public KlientCentrum(String Nazwa_Firmy, String Nazwa_Uslugi) {
        this.Nazwa_Firmy = Nazwa_Firmy;
        this.Nazwa_Uslugi = Nazwa_Uslugi;
    }

    public String getNazwa_Firmy(){
        return Nazwa_Firmy;
    }

    public String getNazwa_Uslugi(){
        return Nazwa_Uslugi;
    }
}
