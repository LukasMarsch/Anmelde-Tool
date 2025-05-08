package org.jufe.anmeldetool.entity;

import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anmeldung {
    private Event event;
    private String vorname;
    private String nachname;
    private InternetAddress mail;
    private PostAdresse adresse;
    private Kirchenbezirk bezirk;
    private String gemeinde;
    private LocalDate geburtstag;
    private Geschlecht geschlecht;
    private Isst isst;
    private List<Essen> anwesend;
    private boolean schwimmer;
    private String anmerkung;
    private Optional<byte[]> einverstaendnisErklaerung;
    private String nameNotfallkontakt;
    private String nummerNutfallkontakt;
    private Optional<Halt> nimmtShuttleVon;
    private Optional<Entschuldigung> entschuldigung;

    public void addEssen(Essen essen) {
        //TODO implement
    }

    public void removeEssen(Essen essen) {
        // TODO implement
    }
}
