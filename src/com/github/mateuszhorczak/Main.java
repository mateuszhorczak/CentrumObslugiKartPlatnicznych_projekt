package com.github.mateuszhorczak;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        while(true) {
            //System.out.println("W jakim miejscu chcesz zrealizowac platnosc");
            //wyswietl
            //sklep, firma, cos 1 - 2 - 3 switche
            //
            //Wybierz rodzaj karty
            //podaj numer karty, (opcjonalnie)imie i nazwisko
            //wyswietlaja sie uslugi i cennim
            //podaj usluge
            //sprawdza czy masz kase
            //jesli masz to potwierdza platnosc, jesli nie to odrzuca



            //Tryb serwisanta
            //Zaloz konto w banku
            //Podaj imie, nazwisko, kwote jaka chcesz wplacic na konto
            //podaj rodzaj karty
            //dostajesz wygenerowany numer konta z przypisana kwota

            //usun konto w banku
            //usuwa karte z kolekcji o danym numerze

            //dodaje wpis z transkakcji do archwium

            Centrum centrum = new Centrum();
            /*
            //Tu bedzie wczytywanie danych z bazy do zmiennej centrum
            */

            int choice, choice2,  value, numerKarty;
            double kwota, rozmiarDebetu;
            String imie, nazwisko, nazwaSklepu;
            Bank bank = new Bank (new ArrayList<>());



            boolean takCzyNie = bank.wczytajDane();
            System.out.println(takCzyNie);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Witaj uzytkowniku gdzie chcesz sie udac??");
                System.out.println("Do banku: 1");
                System.out.println("Do jakiegos sklepu, pokaz liste sklepow: 2");
                System.out.println("Chce wejsc w tryb serwisanta: 3");
                System.out.println("Ide do domu: 0");

                choice = scanner.nextInt();
                switch (choice) {
                    case 0: //Koniec programu
                        System.out.println("Do widzenia!");
                        return;

                    case 1: //Bank
                            //Wydaje mi sie ze jest skonczony
                            //Nie ma jeszcze napewno zadnych (o ile tu sa potrzebne)
                            //wysylania zapytan
                        System.out.println("Do ktorego banku chcesz sie udac? " +
                                "\n" + "Podaj index (zaczyna sie od 0)");
                        centrum.wypiszBanki();
                        value = scanner.nextInt();
                        if (value <= centrum.iloscBankow(centrum) && value >= 0) {
                            bank = centrum.getBanki().get(value);
                            System.out.println("Witaj w banku, co chcesz zrobic?");
                            System.out.println("Chce wplacic pieniadze: 1");
                            System.out.println("Chce wyplacic pieniadze: 2");
                            System.out.println("Chce zalozyc nowa karte: 3");
                            System.out.println("Ide gdzies indziej: 0");
                            value = scanner.nextInt();
                            switch (value) {
                                case 0:
                                    break;
                                case 1:
                                    System.out.println("Podaj numer karty");
                                    numerKarty = scanner.nextInt();
                                    System.out.println("Podaj Kwote");
                                    kwota = scanner.nextDouble();
                                    bank.wplac(numerKarty, kwota);
                                    break;
                                case 2:
                                    System.out.println("Podaj numer karty");
                                    numerKarty = scanner.nextInt();
                                    System.out.println("Podaj Kwote");
                                    kwota = scanner.nextDouble();
                                    bank.wyplac(numerKarty, kwota);
                                    break;
                                case 3:
                                    System.out.println("Podaj imie");
                                    imie = scanner.next();
                                    System.out.println("Podaj nazwisko");
                                    nazwisko = scanner.next();
                                    Osoba osoba = bank.znajdzOsobe(imie, nazwisko);
                                    if (osoba != null) {
                                        System.out.println("Podaj numer karty, musi byc unikalny w tym banku");
                                        numerKarty = scanner.nextInt();
                                        if (centrum.czyNumerKartyJestZajety(numerKarty)) {
                                            System.out.println("Ten numer jest juz zajety!!!");
                                            break;
                                        }
                                        System.out.println("Podaj Kwote");
                                        kwota = scanner.nextDouble();
                                        System.out.println("Jaka chcesz zalozyc karte? " +
                                                "\n" +
                                                "Kredytowa[1], Debetowa[2], Bankomatowa[3]");
                                        choice2 = scanner.nextInt();
                                        switch (choice2) {
                                            case 1:
                                                Karta kartaKredytowa = new KartaKredytowa(numerKarty, kwota);
                                                osoba.dodajKarte(kartaKredytowa);
                                                break;
                                            case 2:
                                                Karta kartaDebetowa = new KartaDebetowa(numerKarty, kwota);
                                                osoba.dodajKarte(kartaDebetowa);
                                                break;
                                            case 3:
                                                Karta kartaBankomatowa = new KartaBankomatowa(numerKarty, kwota);
                                                osoba.dodajKarte(kartaBankomatowa);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                break;
                            }
                        }
                        else {
                            System.out.println("Podano zly indeks banku");
                            break;
                        }
                        break;

                    case 2: //Sklepy
                        centrum.wypiszKlientowCentrum();
                        System.out.println("Ktory sklep wybierasz? Podaj indeks (zaczyna sie od 0)");
                        value = scanner.nextInt();
                        /* Tu trzeba jakos uzyskac nazwe klasy klienta centrum co by podac do
                        // obiektu Wpis informacje, moze metoda .getclass pomoze, sprawdzic trzeba
                        //
                        //if (value <= centrum.iloscSklepow(centrum) && value >= 0) {
                        //    for ()
                        //    nazwaSklepu = instanceof centrum.getKlienciCentrum().get(value);
                        //}
                        */
                        break;

                }


            }
        }

    }
}
