# Anmelde-Tool
## Einleitung

Das Anmeldetool ist ein mehrteiliges Hilfsmittel für die Verwaltung von Teilnehmern bei Events.

Um die Anwesenheiten von Teilnehmern bei Events gemäß der Sorgfaltspflicht aufzunehmen melden sich die Teilnehmer in einer Anmeldemaske im Vorfeld an und werden während des Anmeldeprozesses von Organisatoren und Mitarbeitern überprüft.

## Struktur

![image](https://github.com/user-attachments/assets/89ac219a-cd64-44fd-9fbb-12f4a9e43564)


## Tech-Stack
| Level | Data            | Model       | View              |
|-------|-----------------|-------------|-------------------|
| High  | Spring Data JPA | Spring Boot | Bootstrap, JQuery |
| ----- | Hibernate       | SOA         | Thymeleaf         |
| Low   | H2/Postgres     | MVC         | HTML              |

## User Managemet

User Management wird für den Tracker essentiell.
Vorgeschlagene Rollenaufteilung: Admin > Veranstalter > Helfer

|                                       | Admin | Veranstalter | Helfer |
|---------------------------------------|-------|--------------|--------|
| Teilnehmer An-Abmelden                | x     | x            | x      |
| Teilnahmen von Events einsehen        | x     | x            | x      |
| Event erstellen/löschen               | x     | x            | -      |
| Helfer zu Event hinzufügen            | x     | x            | -      |
| alle Details von Anmeldungen einsehen | x     | x            | -      |
| Helfer / Veranstalter ernennen        | x     | -            | -      |

## Anforderungen

A. Online kann das Alter nicht angezeigt werden, sondern lediglich das Geburtsdatum. Um das Alter am Veranstaltungsbeginn (ohne Kopfrechnen) herauszufinden (z. B. um zu entscheiden, ob eine Einverständniserklärung notwendig ist; oder für bestimmte Dienste infrage kommende Personen zu identifizieren), musste die Teilnehmerliste als Excel exportiert und darin dann eine entsprechende Spalte mit Formel eingerichtet werden. Entsprechend ist online auch keine Filterung nach „volljährig“ o. ä. möglich.

B. Im Rahmen der Anmeldung ist es nicht möglich, die Anforderung einer Einverständniserklärung der Erziehungsberechtigten nur bei Anmeldung zu Veranstaltungsbeginn nicht Volljähriger anzuzeigen (also mit dem Geburtsdatum verknüpft) – und das dann ggf. als Pflicht (allerdings wird es damit schwieriger, Menschen bei Veranstaltungen zur Anmeldung zu motivieren und die Einverständniserklärung nachreichen zu lassen). Aus diesem Grund musste manuell überprüft werden, ob eine Einverständniserklärung erforderlich ist und ob diese vorliegt. Ein sehr aufwändiger Prozess. (vgl. Punkt a) {Hinweis: Selbst, wenn dieser Prozess verbessert würde, müssten die hochgeladenen Dokumente noch dahingehend geprüft werden, ob die richtige Datei vorliegt und unterschrieben wurde. – idealerweise könnte man dann einen Haken setzen, der die Überprüfung dokumentiert – Dennoch wäre es eine große Erleichterung, direkt zu sehen, wer noch nicht volljährig ist

C. Online kann die Teilnehmerliste nicht nach einzelnen Spalten sortiert werden.

D. Wie Hano bereits schrieb, können Angaben der in der Onlineübersicht angezeigten Felder nicht in der Übersicht bearbeitet werden. Stattdessen muss der jeweilige Datensatz geöffnet, zur gewünschten Angabe gescrollt, diese verändert und anschließend die Änderung gespeichert werden, bevor man wieder zurück zur Übersicht wechseln kann. Dies ist sehr umständlich und da das System zudem auch noch relativ langsam ist, auch sehr zeitintensiv. Für die Person, die die Anmeldungen koordiniert, ist das recht mühselig und zeitraubend. Besonders relevant ist es aber bei Check-In (und Infostand für Spätankömmlinge, zwischenzeitliche Abwesenheiten und früher Abreisende): Um bei Notfällen zu wissen, wie viele Personen auf dem Gelände sind (und wer), gibt es einen entsprechend anzupassenden Eintrag (Anwesend: Ja/Nein/Gelände verlassen), der möglichst unkompliziert und schnell anpassbar sein sollte.

E. Zahlungseingänge mussten manuell erfasst werden.
