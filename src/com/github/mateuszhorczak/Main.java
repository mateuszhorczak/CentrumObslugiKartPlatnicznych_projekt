package com.github.mateuszhorczak;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
//elo
public class Main {
    public static void main(String[] args) throws IOException {
        Bank bank1 = new Bank("Bank1");
        bank1.wczytajDane("dane1.txt");
        Bank bank2 = new Bank("Bank2");
        bank2.wczytajDane("dane2.txt");
        Bank bank3 = new Bank("Bank3");
        bank3.wczytajDane("dane3.txt");
        Centrum centrum = new Centrum();
        centrum.dodajBank(bank1);
        centrum.dodajBank(bank2);
        centrum.dodajBank(bank3);
        ArrayList<String> linie = new ArrayList<>();
        int i;

        File plik = new File("archiwum.txt");
        Scanner scannerZPliku = new Scanner(plik);
        while (scannerZPliku.hasNext()) {
            String linia = scannerZPliku.nextLine();
            linie.add(linia);
        }
        plik.createNewFile();
        PrintWriter zapiszDoPliku = new PrintWriter(plik);
        for(i=0; i< linie.size();i++){
            zapiszDoPliku.printf(linie.get(i));
            zapiszDoPliku.printf("\n");
        }

        while(true){
            Scanner scannerKlawiaturax = new Scanner(System.in);
            Scanner scannerKlawiaturay = new Scanner(System.in);
            Scanner scannerKlawiatura1 = new Scanner(System.in);
            Scanner scannerKlawiatura11 = new Scanner(System.in);
            Scanner scannerKlawiatura2 = new Scanner(System.in);
            Scanner scannerKlawiatura22 = new Scanner(System.in);
            Scanner scannerKlawiatura3 = new Scanner(System.in);
            Scanner scannerKlawiatura33 = new Scanner(System.in);
            Scanner scannerKlawiatura111 = new Scanner(System.in);
            Scanner scannerKlawiatura222 = new Scanner(System.in);
            Scanner scannerKlawiatura333 = new Scanner(System.in);
            scannerKlawiatura111.useLocale(Locale.US);
            scannerKlawiatura222.useLocale(Locale.US);
            scannerKlawiatura333.useLocale(Locale.US);
            System.out.println("Witaj, co chcesz zrobić?");
            System.out.println("1 - Zrealizowac platnosc");
            System.out.println("2 - Skorzystac z uslug banku");
            System.out.println("3 - Wejsc w tryb serwisanta");
            System.out.println("4 - Rozmyslilem się - nie chce nic");
            int x = scannerKlawiaturax.nextInt();
            if(x == 4){
                break;
            }
            switch(x){
                case 1:
                    System.out.println("Gdzie chcesz zrealizowac platnosc?");
                    System.out.println("1 - Sklep");
                    System.out.println("2 - Firma Transportowa");
                    System.out.println("3 - Zaklad Uslugowy");
                    System.out.println("4 - Rozmyslilem się - nie chce nic");
                    int y = scannerKlawiaturay.nextInt();
                    int numerKarty1,numerKarty2,numerKarty3;
                    double kwota1,kwota2,kwota3;
                    switch (y){
                        case 1:
                            System.out.println("Podaj nazwe sklepu: ");
                            String nazwaSklepu = scannerKlawiatura11.nextLine();
                            Sklep sklep = new Sklep(nazwaSklepu);
                            System.out.println("Podaj numerKarty: ");
                            numerKarty1 = scannerKlawiatura1.nextInt();
                            System.out.println("Podaj kwote: ");
                            kwota1 = scannerKlawiatura111.nextDouble();
                            boolean czyIstnieje1 = centrum.znajdzNumerKarty(numerKarty1);
                            if(czyIstnieje1 == false){
                                Date aktualnaData1 = new Date();
                                Wpis wpis1 = new Wpis(aktualnaData1, kwota1,sklep,false );
                                centrum.dodajWpis(wpis1);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis1));
                                System.out.println("Podany numer karty nie istnieje");
                                break;
                            }
                            Karta karta1 = centrum.znajdzKarte(numerKarty1);
                            if(!(karta1 instanceof KartaBankomatowa)) {
                                boolean czyZrealizowana1 = karta1.platnosc(kwota1);
                                if(czyZrealizowana1 == true) {
                                    System.out.println("Platnosc zrealizowana pomyslnie! ");
                                }
                                Date aktualnaData1 = new Date();
                                Wpis wpis1 = new Wpis(centrum.znajdzOsobe(numerKarty1), aktualnaData1, kwota1, karta1, sklep,centrum.znajdzBank(numerKarty1),czyZrealizowana1);
                                centrum.dodajWpis(wpis1);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis1));
                                break;
                            }
                            else{
                                Date aktualnaData1 = new Date();
                                Wpis wpis1 = new Wpis(centrum.znajdzOsobe(numerKarty1), aktualnaData1, kwota1, karta1, sklep,centrum.znajdzBank(numerKarty1),false);
                                centrum.dodajWpis(wpis1);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis1));
                                System.out.println("Przepraszamy - nie da sie zaplacic karta bankomatowa");
                                break;
                            }
                        case 2:
                            System.out.println("Podaj nazwe firmy transportowej: ");
                            String nazwaFirmy = scannerKlawiatura2.nextLine();
                            FirmaTransportowa firmaTransportowa = new FirmaTransportowa(nazwaFirmy);
                            System.out.println("Podaj numerKarty: ");
                            numerKarty2 = scannerKlawiatura22.nextInt();
                            System.out.println("Podaj kwote: ");
                            kwota2 = scannerKlawiatura222.nextDouble();
                            boolean czyIstnieje2 = centrum.znajdzNumerKarty(numerKarty2);
                            if(czyIstnieje2 == false){
                                Date aktualnaData2 = new Date();
                                Wpis wpis2 = new Wpis(aktualnaData2, kwota2,firmaTransportowa,false );
                                centrum.dodajWpis(wpis2);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis2));
                                System.out.println("Podany numer karty nie istnieje");
                                break;
                            }
                            Karta karta2 = centrum.znajdzKarte(numerKarty2);
                            if(!(karta2 instanceof KartaBankomatowa)) {
                                boolean czyZrealizowana2 = karta2.platnosc(kwota2);
                                if(czyZrealizowana2 == true) {
                                    System.out.println("Platnosc zrealizowana pomyslnie! ");
                                }
                                Date aktualnaData2 = new Date();
                                Wpis wpis2 = new Wpis(centrum.znajdzOsobe(numerKarty2), aktualnaData2, kwota2, karta2, firmaTransportowa,centrum.znajdzBank(numerKarty2),czyZrealizowana2);
                                centrum.dodajWpis(wpis2);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis2));
                                break;
                            }
                            else{
                                Date aktualnaData2 = new Date();
                                Wpis wpis2 = new Wpis(centrum.znajdzOsobe(numerKarty2), aktualnaData2, kwota2, karta2, firmaTransportowa,centrum.znajdzBank(numerKarty2),false);
                                centrum.dodajWpis(wpis2);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis2));
                                System.out.println("Przepraszamy - nie da sie zaplacic karta bankomatowa");
                                break;
                            }
                        case 3:
                            System.out.println("Podaj nazwe zakladu uslugowego: ");
                            String nazwaZakladUslugowy = scannerKlawiatura3.nextLine();
                            ZakladUslugowy zakladUslugowy= new ZakladUslugowy(nazwaZakladUslugowy);
                            System.out.println("Podaj numerKarty: ");
                            numerKarty3 = scannerKlawiatura33.nextInt();
                            System.out.println("Podaj kwote: ");
                            kwota3 = scannerKlawiatura333.nextDouble();
                            boolean czyIstnieje3 = centrum.znajdzNumerKarty(numerKarty3);
                            if(czyIstnieje3 == false){
                                Date aktualnaData3 = new Date();
                                Wpis wpis3 = new Wpis(aktualnaData3, kwota3,zakladUslugowy,false );
                                centrum.dodajWpis(wpis3);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis3));
                                System.out.println("Podany numer karty nie istnieje");
                                break;
                            }
                            Karta karta3 = centrum.znajdzKarte(numerKarty3);
                            if(!(karta3 instanceof KartaBankomatowa)) {
                                boolean czyZrealizowana3 = karta3.platnosc(kwota3);
                                if(czyZrealizowana3 == true) {
                                    System.out.println("Platnosc zrealizowana pomyslnie! ");
                                }
                                Date aktualnaData3 = new Date();
                                Wpis wpis3 = new Wpis(centrum.znajdzOsobe(numerKarty3), aktualnaData3, kwota3, karta3, zakladUslugowy,centrum.znajdzBank(numerKarty3),czyZrealizowana3);
                                centrum.dodajWpis(wpis3);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis3));
                                break;
                            }
                            else{
                                Date aktualnaData3 = new Date();
                                Wpis wpis3 = new Wpis(centrum.znajdzOsobe(numerKarty3), aktualnaData3, kwota3, karta3, zakladUslugowy,centrum.znajdzBank(numerKarty3),false);
                                centrum.dodajWpis(wpis3);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis3));
                                System.out.println("Przepraszamy - nie da sie zaplacic karta bankomatowa");
                                break;
                            }

                    }
                    break;
                case 2:
                    //Jak bedziesz robic to na biezaco testuj i nie dodawaj niepotrzebnych rzeczy
                    //Tutaj masz zrobic switcha z uslugami banku czyli 1 - wplata, 2- wyplata,
                    // 3- tworzenie konta czyli obiektu osoba, 4- ze wyjscie ze nie chcemy nic
                    //Zwroc uwage jakimi kartami mozesz to zrobic bo nie jestem pewny ale chyba nie wszystkimi
                    //Jak bys chcial sie posugerowac to wyzej zrobilem takie cos ze np bankomatowa placic nie mozna
                    //Tutaj mozna zrobic ze np debetowa nie mozna wplacac czy tam wyplacac to tylko ptrzyklad jak cos
                    //W sumie mozesz zrobic tak ze np zakladamy ze wyplacac i wplacac tylko bankomatowa mozemy
                    //Ale zrob jak uwazasz aby dobrze dzialalo
                    break;
                case 3:
                    //Tutaj tryb serwisanta czyli 1 - mozliwosc wyswietlenia calego calego calgo archiwum z pliku od poczatku
                    //  w tym switchu trzeba bedzie zrobic zeby mozna bylo pzrzsezukiwac te archiwum po danym parametrze
                    // 2 - mozliwosc zobzaczenia oststnich wpisow czyli tych ktore zrobione w trakcie dzialania programu
                    //  w tym switchu trzeba bedzie zrobic zeby mozna bylo pzrzsezukiwac te archiwum po danym parametrze
                    // 3 - nie chcemy nic

                    break;
                default:
                    System.out.println("Bład - sprobuj jeszce raz!");
            }

        }
        bank1.zapiszDane("dane1.txt");
        bank2.zapiszDane("dane2.txt");
        bank3.zapiszDane("dane3.txt");

        zapiszDoPliku.close();
    }
}
