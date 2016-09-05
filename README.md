# PiGuard
Pi Guard jest aplikacja umozliwiajca zrobienie centralki alarmowej z raspberry Pi.
Raspberry Pi posiada porty GPIO, do których mozna podlaczyc urzadzenia wejscia, wyjscia.


**Abstract**
System alarmowy sklada sie z :
- centralki alarmowej - urzadzenie sterujace (tutaj aplikacja)
- sensorow - urzadzen ktore wykrywaja zagrozenie i zglaszaja to do centralki
- sygnalizatorow - urzadzen ktore sygnalizuja sytuacje alarmowa, np syreny badz migajace lampy

Konfiguracja
=======
Link do aplikacji: https://piguard.herokuapp.com/api/v1/









REST Api
=========

Zalozenia
-----
Klasyczne api restowe + HATEOS. 


Zasoby
------

###Slot
Reprezentuje pin GPIO do ktorego podpiety jest sygnalizator badz sensor.
pola 



