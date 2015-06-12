# sise2015
----------------------------
Stan na 12.06
Bartlomiej Sieczka - Dopracowywanie bazy wiedzy CLIPS
Roman Chomik - Zakończenie przekazywania zmiennych do logiki rozmytej, prace nad własną SI
Gniewomir Ciolek - Nic
Tomek Wyroda - ?
----------------------------
Plan na 19.06
Wszyscy - własna SI
----------------------------
Stan na 3.06
Bartlomiej Sieczka - Większość faktów o stanie agenta i jego otoczenia przekazana do CLIPSA
Roman Chomik - Część faktów przekazana do logiki rozmytej
Gniewomir Ciolek - Implementacja mechaniki pozostawiania śladów przez agentów
Tomek Wyroda - ?
----------------------------
Plan na 12.06
Bartlomiej Sieczka - Finalizacja budowania bazy wiedzy CLIPSA, prace nad własnymi agentami
Roman Chomik - Finalizacja ustawiania zmiennych rozmytych, prace nad własnymi agentami
Gniewomir Ciolek - prace nad własnymi agentami
Tomek Wyroda - ?, prace nad własnymi agentami
----------------------------
Plan na 8.05
Bartlomiej Sieczka - 
Roman Chomik - pamięć agentów o ostatnich pokojach i akcjach
Gniewomir Ciolek - ślady
Adam Lukaszewicz - poprawki wyświetlania
Tomek Wyroda - podłączenie jfuzzylogic i clipsjni, podpięcie pod agentów
Dominik Bugala - 
----------------------------
Stan na 24.04
Bartlomiej Sieczka - 
Roman Chomik - Mechanika gry, poruszanie sie, przeszukiwanie pokoji
Gniewomir Ciolek - Poprawiona generacja swiata
Adam Lukaszewicz - Przedmioty, wizualizacja swiata gry
Tomek Wyroda - 
Dominik Bugala - 
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
