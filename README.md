# CentrumObslugiKartPlatniczych-projekt
Projekt w grupie na zajecia z programowania

Centrum obsługi kart płatniczych obsługuje płatności kartami kredytowymi, debetowymi i bankomatowymi. Z żądaniem autoryzacji każdej płatności mogą zwracać się sklepy, zakłady usługowe i firmy transportowe (to są klienci centrum). Centrum autoryzuje płatność po wysłaniu zapytania do banku który wydał daną kartę płatniczą a informację o tym archiwizuje w postaci wpisu w swoim systemie bazodanowym, które można przeszukiwać.

Uwaga: klienci firm są też klientami banku i tam mają konta - nie muszą być rejestrowani w firmach, płatność odbywa się (jak w rzeczywistości) podając dane karty i kwotę. Są 2 relacje klient: firma jest klientem centrum, osoba jest klientem firmy i zarazem klientem banku, w którym ma konto.

Minimalny zakres funkcjonalności:
zarządzenie firmami korzystającymi z centrum centrum i bankami (dodawanie, usuwanie, przegląd)
zarządzanie kartami (przypisana do klienta banku, wydana przez bank)
obsługa żądań autoryzacji (kwota, data, dane karty) - decyzja zależna od rodzaju karty
zapis i odczyt stanu systemu na dysk (realizacja archiwum operacji w postaci odrębnego pliku)
przeszukiwanie archiwum za pomocą złożonych warunków (firma, bank, numer karty, właściciel, kwota, warunki OR i AND)
