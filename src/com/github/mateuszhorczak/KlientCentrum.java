package com.github.mateuszhorczak;

public abstract class KlientCentrum {
    protected String nazwaFirmy;

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public KlientCentrum() {
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

}
