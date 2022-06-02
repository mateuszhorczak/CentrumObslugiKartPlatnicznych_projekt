package com.github.mateuszhorczak;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

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

        File plik2 = new File("archiwum.txt");
        plik2.createNewFile();
        PrintWriter zapiszDoPliku = new PrintWriter("archiwum.txt");

        while(true){
            Scanner scannerKlawiatura = new Scanner(System.in);
            Scanner scannerKlawiatura1 = new Scanner(System.in);
            System.out.println("Witaj, co chcesz zrobić?");
            System.out.println("1 - Zrealizowac platnosc");
            System.out.println("2 - Skorzystac z uslug banku");
            System.out.println("3 - Wejsc w tryb serwisanta");
            System.out.println("4 - Rozmyslilem się - nie chce nic");
            int x = scannerKlawiatura.nextInt();
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
                    int y = scannerKlawiatura.nextInt();
                    int numerKarty;
                    int kwota;
                    switch (y){
                        case 1:
                            System.out.println("Podaj nazwe sklepu: ");
                            String nazwaSklepu = scannerKlawiatura1.nextLine();
                            Sklep sklep = new Sklep(nazwaSklepu);
                            System.out.println("Podaj numerKarty: ");
                            numerKarty = scannerKlawiatura.nextInt();
                            System.out.println("Podaj kwote: ");
                            kwota = scannerKlawiatura.nextInt();
                            boolean czyIstnieje = centrum.znajdzNumerKarty(numerKarty);
                            if(czyIstnieje == false){
                                Date aktualnaData = new Date();
                                Wpis wpis = new Wpis(aktualnaData, kwota,sklep,false );
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                System.out.println("Podany numer karty nie istnieje");
                                break;
                            }
                            Karta karta = centrum.znajdzKarte(numerKarty);
                            if(!(karta instanceof KartaBankomatowa)) {
                                boolean czyZrealizowana = karta.platnosc(kwota);
                                if(czyZrealizowana == true) {
                                    System.out.println("Platnosc zrealizowana pomyslnie! ");
                                }
                                Date aktualnaData = new Date();
                                Wpis wpis = new Wpis(centrum.znajdzOsobe(numerKarty), aktualnaData, kwota, karta, sklep,centrum.znajdzBank(numerKarty),czyZrealizowana);
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                break;
                            }
                            else{
                                Date aktualnaData = new Date();
                                Wpis wpis = new Wpis(centrum.znajdzOsobe(numerKarty), aktualnaData, kwota, karta, sklep,centrum.znajdzBank(numerKarty),false);
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                System.out.println("Przepraszamy - nie da sie zaplacic karta bankomatowa");
                                break;
                            }

                    }
                    break;
                case 2:
                case 3:
                case 4:
            }

        }
        centrum.wypiszWpisy();
        zapiszDoPliku.close();
    }
}
