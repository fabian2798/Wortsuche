# Mein Spring-Anwendungsname

Diese Spring-Anwendung bietet verschiedene API-Endpunkte, um Vorlagen abzurufen und gefundene Wörter in den Vorlagen zu suchen.

## Lokale Entwicklung

Der Server ist unter `localhost:8080` erreichbar.

## Datenbank

Die Anwendung verwendet eine MongoDB-Datenbank zur Speicherung der Vorlagen.

### Dateischema in der Datenbank

Jede gespeicherte Datei in der Datenbank folgt diesem Schema:

- **id:** Eindeutige Kennung der Vorlage in der Datenbank.
- **title:** Titel der Vorlage.
- **loesungswoerter:** Liste von Lösungswörtern.
- **grid:** Ein 2-dimensionales Array aus Zeichen (Chars), das das Raster der Vorlage repräsentiert.

Beispiel:

```json
{
  "_id": ObjectId("6123456789abcdef01234567"),
  "title": "Vorlage Beispiel",
  "loesungswoerter": ["Wort1", "Wort2", "Wort3"],
  "grid": [
    ["A", "B", "C"],
    ["D", "E", "F"],
    ["G", "H", "I"]
  ]
}
```

## API-Endpunkte

### Alle Vorlagen anzeigen
- Endpoint: `/api/Vorlagen`
- Beschreibung: Liefert alle verfügbaren Vorlagen zurück.

### Vorlage nach ID suchen
- Endpoint: `/api/Vorlagen/{id}`
- Beschreibung: Liefert die Vorlage mit der angegebenen ID zurück.

### Vorlage nach Titel suchen
- Endpoint: `/api/Vorlagen/ByTitle?title={title}`
- Beschreibung: Sucht nach Vorlagen mit dem angegebenen Titel und liefert sie zurück.

### Gefundene Wörter in den Vorlagen abrufen (v1, v2, v3)
- Endpunkte:
  - `/api/Vorlagen/foundwords/v1`
  - `/api/Vorlagen/foundwords/v2`
  - `/api/Vorlagen/foundwords/v3`
- Beschreibung: Liefert die gefundenen Wörter in den Vorlagen mit Angaben zu Zeilen- oder Spaltennummern, Ausrichtung, Startindex und Endindex zurück.

## Beispielaufrufe

- Um alle Vorlagen anzuzeigen:
    `GET http://localhost:8080/api/Vorlagen`

- Um nach einer Vorlage mit einer bestimmten ID zu suchen (ersetze `{id}` durch die tatsächliche ID):
    `GET http://localhost:8080/api/Vorlagen/{id}`
  
- Um nach einer Vorlage mit einem bestimmten Titel zu suchen (ersetze `{title}` durch den tatsächlichen Titel):
    `GET http://localhost:8080/api/Vorlagen/ByTitle?title={title}`
  
- Um die gefundenen Wörter in den Vorlagen abzurufen (v1, v2, v3):
    `GET http://localhost:8080/api/Vorlagen/foundwords/v1`
    `GET http://localhost:8080/api/Vorlagen/foundwords/v2`
    `GET http://localhost:8080/api/Vorlagen/foundwords/v3`
