package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity //TODO muss das nicht hier hin? war missing... kein Internet im Zug, kann nicht bauen, weil packages fehlen, kann also nicht gegen die H2 gegenchecken
public class Teilnehmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Boolean angekommen;
    private boolean anwesend;
    private boolean bestaetigungVersendet;

    @Enumerated(EnumType.STRING)
    private Rolle rolle;

    public Teilnehmer(Anmeldung anmeldung) {
        //TODO implement
    }

    public void addAufgabe(String aufgabe) {
        //TODO String als return ??? implement
    }

    public void removeAufgabe(String aufgabe) {
        //TODO Stirng als return ??? implement
    }

    public Alter alter() {
        //TODO Alter aus Geburtstag von Teilnehmer und erstem Eventtag (get Event from Anmeldung) berrechnen
        return Alter.O18;
    }
}
