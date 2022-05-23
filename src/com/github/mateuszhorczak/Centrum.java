package com.github.mateuszhorczak;
import java.util.List;

public class Centrum {
    private List<Wpis> archiwum;
    private List<KlientCentrum> klienciCentrum;
    private List<Bank> banki;

    public void dodajWpis(Wpis wpis){
        archiwum.add(wpis);
    }

    public void usunWpis(Wpis wpis){
        archiwum.remove(wpis);
    }

    public boolean wyslijZapytanie(int numerKarty, double kwota){
        return true;
    }

    public boolean czyIstniejeKlient(String nazwaFirmy){
        for(var item: klienciCentrum){
            if (item.getNazwa_Firmy() == nazwaFirmy){
                return true;
            }
        }
        return false;
    }

    public boolean dodajKlient(KlientCentrum klient){
        if (!czyIstniejeKlient(klient.Nazwa_Firmy)){
            klienciCentrum.add(klient);
            return true;
        }
        return false;
    }

    public boolean usunKlient(KlientCentrum klient){
        if (czyIstniejeKlient(klient.Nazwa_Firmy)){
            klienciCentrum.remove(klient);
            return true;
        }
        return false;
    }
}
