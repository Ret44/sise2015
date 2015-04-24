# sise2015
----------------------------
Plan 23.04
Bartlomiej Sieczka - Wstepna implementacja CLIPSJNI
Roman Chomik - Mechanika gry, poruszanie sie, przeszukiwanie pokoji
Gniewomir Ciolek - Poprawiona generacja swiata, slady zostawiane przez graczy
Adam Lukaszewicz - Przedmioty, wizualizacja swiata gry
Tomek Wyroda - ?
Dominik Bugala - ?
----------------------------
Stan na 17.04
Bartlomiej Sieczka - Interakcja uzytkownika z agentem
Roman Chomik - Struktura swiata gry
Gniewomir Ciolek - Generacja swiata
----------------------------


Koncept:
Świat:
-Plansza składa się z Tiles'ów i jest to tablica NxN
-Plansza jest generowana Losowo-Każdy tiles to pokój
-Pokój nie musi posiadać wszystkich przejść do sąsiednich pokoi
-W pokoju może znajdować się przedmiot zwiększający/zmniejszający statystyki gracza
-W pokoju po przejściu gracza zostaje ślad który zanika z każdą turą
-Jeden cios -> śmierć
-Gracz posiada jeden parametr, który decyduje o tym, który z agentów wygra pojedynek
-Gracz pamięta ostatnich 10 odwiedzonych pokoi i jego akcje w tych pokojach
Akcje graczy:
-Gracz może poruszać się pomiędzy pokojami
-Gracz może czytać ślady w pokoju
-Gracz decyduje czy podążać za śladem czy nie
-Gracz może szukać przedmiotu w pokoju i go podnosić
