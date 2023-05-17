# 2D Shooter

## Paskirtis

Žaidimas, kuriame žaidėjo tikslas yra kuo ilgiau išgyventi, šaudant priešus.

## Paleidimas

Programa paleidžiama prijungus telefoną su Android 8.0+ operacine sistema, arba turint emuliatorių (
SDK 26+).

## Funkcionalumas

- 3 Žaidimo sunkumo lygiai, pasirenkami paleidus programą.
- 2 joystick'ai, tiek iššaunamų kulkų trajektorijai nukreipti, tiek judėjimui.
- Priešai juda žaidėjo link ir iš paskos dėlioja minas, kurias palieetus atimami gyvybės taškai.
- Nukovus priešą gaunami XP taškai, kuriais yra keliamas žaidėjo lygis.
- Kylant lygiui, kinta priešų stiprumas ir žaidėjo šaudymo greitis.
- Pasibaigus gyvybėms žaidimas baigiamas.
- Galimybė išsaugoti žaidimo būseną ir ją atstatyti.

## Pagrindinės klasės

- `GameLoop` - Pagrindinė programos gija, keičia žaidimo būseną 60 kartų per sekundę.
- `Game` - Pagrindinė programos klasė, atsakinga už žaidimo objektų funkcionalumą.
- `Player` - Žaidėjo valdomas objektas.
- `Constants` - Saugo programos parametrus.
- `TileMap` - Klasė, valdanti pasaulio žemėlapį.
- `JoyStick` - UI elementai, leidžiantys žaidėjui funkcionuoti.
- `StartScreenActivity` - Klasė, rodanti programos meniu.

## Plėtimo galimybės

- Visų žaidimo objektų animacijų kūrimas.
- Galimybė keisti/tobulinti turimus ginklus.
- Priešų rūšių kūrimas.
- Galimybė žaisti žaidimą keliese (Multiplayer).
- Ekonomikos pridėjimas.

## Projektavimo šablonai

- `Tile` - Gamyklos šablonas.
- `Game` - Iteratorius.