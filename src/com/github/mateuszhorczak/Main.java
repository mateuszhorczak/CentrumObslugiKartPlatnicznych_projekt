package com.github.mateuszhorczak;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        ArrayList<String> linie = new ArrayList<>();
        int i;

        File plik = new File("archiwum.txt");
        Scanner scannerZPliku = new Scanner(plik);
        while (scannerZPliku.hasNext()) {
            String linia = scannerZPliku.nextLine();
            linie.add(linia);
        }
        if (!plik.createNewFile()) {
            System.out.println("Problem z plikiem");
            return;
        }
        PrintWriter zapiszDoPliku = new PrintWriter(plik);
        for (i = 0; i < linie.size(); i++) {
            zapiszDoPliku.printf(linie.get(i));
            zapiszDoPliku.printf("\n");
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Witaj, co chcesz zrobić?");
            System.out.println("1 - Zrealizowac platnosc");
            System.out.println("2 - Skorzystac z uslug banku");
            System.out.println("3 - Wejsc w tryb serwisanta");
            System.out.println("4 - Rozmyslilem się - nie chce nic");
            int x = scanner.nextInt();
            if (x == 4) {
                break;
            }
            switch (x) {
                case 1:
                    System.out.println("\nGdzie chcesz zrealizowac platnosc?");
                    System.out.println("1 - Sklep");
                    System.out.println("2 - Firma Transportowa");
                    System.out.println("3 - Zaklad Uslugowy");
                    System.out.println("4 - Rozmyslilem się - nie chce nic");
                    int y = scanner.nextInt();
                    switch (y) {
                        case 1:
                            dodajKlientCentrum(centrum, zapiszDoPliku, new Sklep());
                            break;
                        case 2:
                            dodajKlientCentrum(centrum, zapiszDoPliku, new FirmaTransportowa());
                            break;
                        case 3:
                            dodajKlientCentrum(centrum, zapiszDoPliku, new ZakladUslugowy());
                            break;
                    }


                case 2:
                    System.out.println("Do ktorego banku chcesz sie udac, podaj index (zaczyna sie od 0)");
                    centrum.wypiszBanki();
                    int numerBanku = scanner.nextInt();

                    System.out.println("Witaj w banku!");
                    System.out.println("Co chcesz zrobic?");
                    System.out.println("Wplacic pieniadze do banku: 1");
                    System.out.println("Wyplacic pieniadze z banku: 2");
                    System.out.println("Dodac nowa karte: 3");
                    System.out.println("Wychodze z banku: 4");
                    int z = scanner.nextInt();

                    if (z == 4) {
                        System.out.println("Do widzenia!");
                        break; //TODO sprawdzic czy wychodzi
                    }

                    int numerKarty = 0;
                    double kwota = 0;

                    if (z == 1 || z == 2) {
                        System.out.println("Podaj numerKarty: ");
                        numerKarty = scanner.nextInt();
                        System.out.println("Podaj kwote: ");
                        kwota = scanner.nextDouble();

                        boolean czyIstnieje = centrum.czyIstniejeNumerKarty(numerKarty);
                        if (!czyIstnieje) {
                            Date aktualnaData1 = new Date();
                            Wpis wpis = new Wpis(centrum.znajdzOsobePoNumerze(numerKarty),
                                    aktualnaData1,
                                    kwota,
                                    centrum.znajdzKarte(numerKarty),
                                    centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty),
                                    false,
                                    "Karta nie istnieje"
                            );
                            centrum.dodajWpis(wpis);
                            zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                            System.out.println("Podany numer karty nie istnieje");
                            break;
                        }
                    }

                    switch (z) {
                        case 1:
                            try {
                                centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty).wplac(numerKarty, kwota);

                                Wpis wpis = new Wpis(
                                        centrum.znajdzOsobePoNumerze(numerKarty),
                                        new Date(),
                                        kwota,
                                        centrum.znajdzKarte(numerKarty),
                                        centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty),
                                        true,
                                        "Wplata"
                                );
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                System.out.println("Poprawnie uiszczone wplate na konto");

                            } catch (CardNotFoundException exception) {
                                System.out.println("Karta nie istnieje");
                            }

                            break;
                        case 2:
                            try {
                                centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty).wyplac(numerKarty, kwota);

                                Wpis wpis = new Wpis(
                                        centrum.znajdzOsobePoNumerze(numerKarty),
                                        new Date(),
                                        kwota,
                                        centrum.znajdzKarte(numerKarty),
                                        centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty),
                                        true,
                                        "Wyplata"
                                );
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                System.out.println("Wyplata pieniedzy powiodla sie!");

                            } catch (CardNotFoundException exception) {
                                System.out.println("Karta nie istnieje");
                            } catch (InvalidCardDataException exception) {
                                Wpis wpis = new Wpis(
                                        centrum.znajdzOsobePoNumerze(numerKarty),
                                        new Date(),
                                        kwota,
                                        centrum.znajdzKarte(numerKarty),
                                        centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty),
                                        false,
                                        "Wyplata"
                                );
                                centrum.dodajWpis(wpis);
                                zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                                System.out.println("Na karcie nie ma wystarczajacych srodkow!");
                            }

                            break;
                        case 3:
                            System.out.println("Jaka karte chcesz dodac?");
                            System.out.println("Karta Bankomatowa: 1");
                            System.out.println("Karta Debetowa: 2");
                            System.out.println("Karta Kredytowa: 3");
                            int rodzajKarty = scanner.nextInt();

                            System.out.println("Podaj numer karty");
                            int nrKarty = scanner.nextInt();

                            if (centrum.czyNumerKartyJestZajety(nrKarty)) {
                                System.out.println("Podany numer karty jest juz zajety!");
                                break;
                            }

                            System.out.println("Podaj kwote jaka chcesz wplacic lub limit karty do uzyskania");
                            double kwotaWplacenia = scanner.nextDouble();

                            System.out.println("Podaj swoje imie: ");
                            String imie = scanner.next();
                            System.out.println("Podaj swoje nazwisko: ");
                            String nazwisko = scanner.next();
                            Osoba osoba = centrum.znajdzOsobePoImieniuINazwisku(imie, nazwisko);

                            if (osoba == null) {
                                osoba = new Osoba(imie, nazwisko);
                            }
                            Bank bank = centrum.getBanki().get(numerBanku);
                            bank.dodajDobryTypKarty(rodzajKarty, nrKarty, kwotaWplacenia, osoba);
                            bank.dodajOsobe(osoba);

                            Wpis wpis = new Wpis(
                                    centrum.znajdzOsobePoNumerze(nrKarty),
                                    new Date(),
                                    kwotaWplacenia,
                                    centrum.znajdzKarte(numerKarty),
                                    bank,
                                    true,
                                    "Wydano nowa karte!"
                            );
                            centrum.dodajWpis(wpis);
                            zapiszDoPliku.printf(centrum.pobierzWpis(wpis));
                            System.out.println("O to twoja karta!");
                    }
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

    private static boolean czyNieIstniejeNumerKarty(Centrum centrum, PrintWriter zapiszDoPliku, double kwota2, KlientCentrum firmaTransportowa, boolean czyIstnieje2) {
        if (czyIstnieje2) {
            return false;
        }

        Date aktualnaData2 = new Date();
        Wpis wpis2 = new Wpis(aktualnaData2, kwota2, firmaTransportowa, false);
        centrum.dodajWpis(wpis2);
        zapiszDoPliku.printf(centrum.pobierzWpis(wpis2));
        System.out.println("Podany numer karty nie istnieje");
        return true;
    }

    private static void czyNieBankomatowa(
            Centrum centrum,
            PrintWriter zapiszDoPliku,
            int numerKarty3,
            double kwota3,
            KlientCentrum zakladUslugowy,
            Karta karta3
    ) {
        if (!(karta3 instanceof KartaBankomatowa)) {
            boolean czyZrealizowana3 = karta3.platnosc(kwota3);
            if (czyZrealizowana3) {
                System.out.println("Platnosc zrealizowana pomyslnie! ");
            }
            Date aktualnaData3 = new Date();
            Wpis wpis3 = new Wpis(
                    centrum.znajdzOsobePoNumerze(numerKarty3),
                    aktualnaData3,
                    kwota3,
                    karta3,
                    zakladUslugowy,
                    centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty3),
                    czyZrealizowana3
            );

            centrum.dodajWpis(wpis3);
            zapiszDoPliku.printf(centrum.pobierzWpis(wpis3));
            return;
        }
        Date aktualnaData3 = new Date();
        Wpis wpis3 = new Wpis(centrum.znajdzOsobePoNumerze(numerKarty3),
                aktualnaData3,
                kwota3,
                karta3,
                zakladUslugowy,
                centrum.znajdzBankWKtorymJestTenNumerKarty(numerKarty3),
                false
        );

        centrum.dodajWpis(wpis3);
        zapiszDoPliku.printf(centrum.pobierzWpis(wpis3));
        System.out.println("Przepraszamy - nie da sie zaplacic karta bankomatowa");
    }


    public static void dodajKlientCentrum(Centrum centrum, PrintWriter printWriter, KlientCentrum klientCentrum) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwe zakladu uslugowego: ");
        scanner.nextLine();
        String nazwaKlientCentrum = scanner.nextLine();
        klientCentrum.setNazwaFirmy(nazwaKlientCentrum);
        System.out.println("Podaj numerKarty: ");
        int numerKarty = scanner.nextInt();
        System.out.println("Podaj kwote: ");
        double kwota = scanner.nextDouble();

        if (czyNieIstniejeNumerKarty(
                centrum,
                printWriter,
                kwota,
                klientCentrum,
                centrum.czyIstniejeNumerKarty(numerKarty))
        ) {
            return;
        }

        Karta karta = centrum.znajdzKarte(numerKarty);
        czyNieBankomatowa(centrum, printWriter, numerKarty, kwota, klientCentrum, karta);
    }
}
