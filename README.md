# Studienarbeit_Performanceanalyse_Collections_Java
 Code zur Durchführung der Benchmarks der Studienarbeit "Analyse von Collection Frameworks - Grundlagen und Performanceevaluation am Beispiel von Java".

## Anforderungen
Gearbeitet wurde mit Open JDK 19. 

Bei Projekt handelt es sich um ein Maven-Projekt.

Als Editor wurde  IntelliJ Community Edition verwendet. Um auch Benchmarks mit hoher Elementzahl durchzuführen, musste eine JVM-Einstellung vorgenommen werden. In IntelliJ geht man dafür oben rechts neben dem Hammer auf die "Run-Configuration". Bei der aktuellen (wenn vorhanden) oder einer neuen Konfiguration (wenn nicht vorhanden -> Maven Template) kann dann "Modify Options" -> "Add VM Options" -> Eintragen "-Xmx8192m", bzw. 8192 durch 50% des aktuellen Hauptspeichers ersetzen. 

## Anwendung
Die Hauptklasse ist Controller. In der main-Methode müssen einfach die gewollten Use Cases ein oder auskommentiert werden. Sie werden dann jeweils ausgeführt und dokumentiert. 

Die Hilfsobjekte die im Benchmark verwendet werden, sind jeweils unter dem Ordner "CollectionsInit" konfiguriert und werden dem Benchmark schließlich übergeben.

Die tatsächlichen Benchmarks sind unter dem Ordner UseCases -> Static eingeordnet. Konfiguriert werden sie immer in der Methode "RunBenchmarks". Ausgeführt werden alle Methoden mit der Annotation @Benchmark. 

## Verbesserungspotential
Zur Darstellung der Ergebnisse als Tabelle wurde Apache Poi verwendet. Die Art der Darstellung könnte einmalig mit etwas Mehraufwand besser konfiguriert werden, damit keine manuelle Nachbearbeitung mehr notwendig wäre. 
Dazu müsste der ExcelTableGenerator verändert werden: https://github.com/svsjo/Studienarbeit_Performanceanalyse_Collections_Java/blob/58bfbe3f3742d4510b3908d48e260c664c96b98c/src/main/java/org/example/Utils/ExcelTableGenerator.java.

Zur Darstellung der Ergebnisse als Diagramm wurde JFeeChart verwendet. Im Nachhinein konnte festgestellt werden, dass die Diagramme standardmäßig eine zu kleine Legende besitzen und die Farben auf dem Papier teilweise recht schwer erkennbar sind. Hiermit müsste genauer experimentiert werden um eine optimale Konfiguration einmalig zu erstellen. 
Dazu müsste der DiagramDrawer verändert werden: https://github.com/svsjo/Studienarbeit_Performanceanalyse_Collections_Java/blob/58bfbe3f3742d4510b3908d48e260c664c96b98c/src/main/java/org/example/Utils/DiagramDrawer.java. 
