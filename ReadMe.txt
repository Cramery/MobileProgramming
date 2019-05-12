Technische Anforderungen:
Verwendung eines sinnvollen Foreground-Services:
	Die Elemente der Einkaufsliste können als Foreground Service gestartet werden. 
	Nach dem Starten wird das erste Element der Einkaufsliste in der Notification angezeigt. Durch "Next" und "Previous" kann das nächste Einzukaufende angezeigt werden.

Lokale Persistenz mittels Preferences:
	Die Login Daten werden nach dem Einloggen als Shared Preferences gespeichert.
	Die Logindaten werden nach dem Schliessen und erneuten Öffnen geladen, um danach zu entscheiden, ob die Login-Seite oder das Profil angezeigt werden soll
	Die angezeigtn Daten zum User werden aus den Shared Preferences geladen

Lokale Persistenz mittels Datenbank:
	Die Einkaufsliste unter dem Punk "Shopping" wird auf einer lokalen RoomDB persistiert
	Es können sowohl neue Elemente hinzugefügt, sowie eine Liste aller gespeicherten Elemente angezeigt werden

Verwendung von nicht im Modul behandelten Bibiliotheken/Frameworks
	Firebase: Die Rezepte werden in einer Cloud Firestore Datenbank auf Firebase gespeichert.
	Die Rezepte können auf diese Datenbank geschrieben und von dieser gelesen werden

Verwendung einfacher Nebenläufigkeit:
	Das Hinzufügen eines neuen Produkts zur Einkaufsliste wird als asynchroner Task ausgeführt
	Das Lesen aller Rezepte sowie der Übersicht aller Rezepttitel geschieht asynchron
	Das Einloggen/Registrieren wird asynchron ausgeführt

Fragments:
	Alle Layouts sind mit Fragments aufgebaut und werden dynamisch auf den Screen geladen
