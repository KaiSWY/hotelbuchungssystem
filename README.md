# Hotelbuchungssystem
Das Programm erlaubt Buchungen in einem Hotel zu verwalten. Dabei können Nutzer erstellt und Buchungen vorgenommen werden. Diese können dann auch wieder storniert werden. 

Features:
- Benutzer anlegen
- Buchungen von Zimmern, Parkplätzen, Aktivitäten und Restauranttischen  vornehmen und stornieren
- Analysen über die Buchungen erstellen 

Das Programm kann über die .jar Datei im presentation-Ordner gestartet werden. Dabei müssen die nötigen Kommandos hinter den Aufruf java -jar <jar-file> geschrieben werden.

Die möglichen Kommandos mit der richtigen Schreibweise können über das Enum MainCommands im Programmcode ermittelt werden.

Beispielkommando zur Nutzererstellung:
java -jar Hotelbuchungssystem.jar createUser --firstName Max --lastName Mustermann --mail m@m.de