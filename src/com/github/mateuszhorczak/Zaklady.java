package com.github.mateuszhorczak;

import java.util.List;

public class Zaklady {
   private List<FirmaTransportowa> firmyTransportowe;
   private List<ZakladUslugowy> zakladyuslugowe;
   private List<Sklep> sklepy;
   private List<Osoba> osoby;

   public void dodajFirma(FirmaTransportowa nazwa) {
       firmyTransportowe.add(nazwa);
   }
   public void dodajZaklad(ZakladUslugowy nazwa) {
       zakladyuslugowe.add(nazwa);
   }
   public void dodajSklep(Sklep nazwa) {
        sklepy.add(nazwa);
   }
    public void usunFirma(FirmaTransportowa nazwa) {
        firmyTransportowe.remove(nazwa);
    }
    public void usunZaklad(ZakladUslugowy nazwa) {
        zakladyuslugowe.remove(nazwa);
    }
    public void usunSklep(Sklep nazwa) {
        sklepy.remove(nazwa);
    }

    public void dodajOsobe(Osoba osoba) {
       osoby.add(osoba);
    }

    public void usunOsobe(Osoba osoba) {
        osoby.remove(osoba);
    }


}
