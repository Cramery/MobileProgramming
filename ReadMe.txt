Technische Anforderungen:
Verwendung eines sinnvollen Foreground-Services:
	xy

Lokale Persistenz mittels Preferences:
	Die Login Daten werden nach dem Einloggen als Shared Preferences gespeichert

Lokale Persistenz mittels Datenbank:
	Die Einkaufsliste unter dem Punk "Shopping" wird auf einer lokalen RoomDB persistiert

Verwendung von nicht im Modul behandelten Bibiliotheken/Frameworks
	Firebase: Die Rezepte werden in einer Cloud Firestore Datenbank auf Firebase persistiert.
	Die Rezepte können auf diese Datenbank geschrieben und von dieser gelesen werden

Verwendung einfacher Nebenläufigkeit:
	Das Hinzufügen eines neuen Produkts zur Einkaufsliste wird als asynchroner Task ausgeführt
	Das Lesen aller Rezepte sowie der Übersicht aller Rezepttitel geschieht asynchron
	Das Einloggen/Registrieren wird asynchron ausgeführt

Fragments:
	Alle Layouts sind mit Fragments aufgebaut und werden dynamisch auf den Screen geladen
