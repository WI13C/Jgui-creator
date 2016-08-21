#Architecture Description

##Modules

###Data

Enthält alle Datenstrukturen die in mehreren Modulen benötigt werden. Dazu gehören:
- Datenstruktur für Objekte
- Validatoren

###Parser

Modul um die annotierten Klassen in die definierte Datensturktur zu parsen.

###GUI

Modul um as der Datenstruktur Swing Oberflächen zu generieren und die Änderungen an die Datenstruktur zurück zu geben.

###Framework

Schnittstelle des Frameworks nach außen. Hier werden die Module zusammen gefügt.

##Schnittstellen

TODO




#Projekt

##In Eclipse importieren
Eclipse -> Import -> Existing Maven Projects -> Code Ordner auswählen und alle pom.xml auswählen.

##Maven in der Kommandozeile
[Laden](https://maven.apache.org/download.cgi)
[Installieren](https://maven.apache.org/install.html)
[Nutzen](https://maven.apache.org/run.html)

##Projekt bauen
`mvn clean install' im code Ordner ausführen.
Die fertige Jar Datei befindet sich dann unter framework/target/


