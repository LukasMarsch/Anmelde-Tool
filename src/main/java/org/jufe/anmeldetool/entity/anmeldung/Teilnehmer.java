package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Teilnehmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Boolean angekommen;
    private boolean anwesend;
    private boolean bestaetigung;
    private List<String> aufgaben;
    @Enumerated
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
