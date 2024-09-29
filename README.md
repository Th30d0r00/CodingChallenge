# Dokumentation Versicherungsprämienrechner
--- 
### Datenbank

Die Anwendung nutzt eine **H2-Datenbank**, um eingegebene Daten im Versicherungsprämienrechner persistent zu speichern. Der Speicherort der Datenbankdatei kann über die `application.properties`-Datei konfiguriert werden. Die Wahl fiel auf H2, da diese Datenbank direkt in der Java-Anwendung eingebettet ist und keine separate Installation eines Datenbankservers wie `PostgreSQL` oder `MySQL` erforderlich ist.

**Zugriff auf die H2-Datenbank über das Web:**

- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:file:./data/h2db`
- Benutzername: `sa`
- Passwort:
  
### Services

Die Anwendung beinhaltet zwei zentrale Services:

1. **PostCodeService**: Dieser Service liest die Datei `postcodes.csv` ein und stellt Funktionen bereit, um Informationen basierend auf Postleitzahlen abzurufen.
    
2. **PraemieService**: Dieser Service berechnet die Versicherungsprämie auf Grundlage von Faktoren wie geschätzten Kilometern, Fahrzeugtyp und Bundesland. Er greift per **Dependency Injection** auf den `PostCodeService` zu, um das Bundesland anhand der Postleitzahl zu ermitteln.

### Tests

Für die **Testautomatisierung** wurde das Framework **JUnit** in Kombination mit **Mockito** eingesetzt. Tests wurden implementiert, um sicherzustellen, dass:

- Die **Prämienberechnung** korrekt erfolgt, basierend auf den Faktoren für geschätzte Kilometer, Fahrzeugtyp und Bundesland.
- Die **CSV-Datei** ordnungsgemäß eingelesen und die darin enthaltenen Daten korrekt verarbeitet werden.

### Frontend
Das Frontend wurde mit dem **Vaadin-Webframework** entwickelt, das eine benutzerfreundliche und moderne Weboberfläche für die Interaktion mit dem Prämienrechner bietet.

---
