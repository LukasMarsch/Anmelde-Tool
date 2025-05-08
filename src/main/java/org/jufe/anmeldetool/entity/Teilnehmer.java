package org.jufe.anmeldetool.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Teilnehmer {
    private Boolean angekommen;
    private boolean anwesend;
    private boolean bestautigung;
    private List<String> aufgaben;
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
    }
}
