package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.BaseEntity;
import org.jufe.anmeldetool.entity.entschuldigung.Entschuldigung;
import org.jufe.anmeldetool.entity.event.Essen;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Anmeldung extends BaseEntity implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @NonNull
    private Event event;

    private String vorname;

    private String nachname;

    private InternetAddress mail;

    private PostAdresse adresse;

    private Kirchenbezirk bezirk;

    private String gemeinde;

    private LocalDate geburtstag;

    @Enumerated(EnumType.STRING)
    private Geschlecht geschlecht;

    @Enumerated(EnumType.STRING)
    private Isst isst;

    @ManyToMany
    private Set<Essen> mahlzeitenAnwesend;

    private boolean schwimmer = false;

    @Nullable
    private String anmerkung;

    @Nullable
    private byte[] einverstaendnisErklaerung;

    private String nameNotfallkontakt;

    private String nummerNotfallkontakt;

    @ManyToOne
    @Nullable
    private Halt nimmtShuttleVon;

    @OneToOne
    private Entschuldigung entschuldigung;

    private boolean bestaetigt = false;

    private boolean angekommen = false;

    private boolean anwesend = false;

    @Enumerated(EnumType.STRING)
    private Rolle rolle;

    public Anmeldung(Event event) {
        this.event = event;
    }

    public void addEssen(Essen essen) {
        mahlzeitenAnwesend.add(essen);
    }

    public void removeEssen(Essen essen) {
        mahlzeitenAnwesend.remove(essen);
    }

    public Alter alter() throws IllegalArgumentException {
        int alter = geburtstag.until(event.getVon())
                              .getYears();
        if (alter < 0) {
            throw new IllegalArgumentException("Alter kann nicht negativ sein");
        }
        if (alter >= 18) {
            return Alter.O18;
        } else if (alter >= 16) {
            return Alter.U18;
        } else if (alter >= 0) {
            return Alter.U16;
        }
        throw new IllegalArgumentException("Alter muss erreichbar sein.");
    }

    public Alter alter() throws IllegalArgumentException {
        Period alter = geburtstag.until(event.getVon());
        if (alter.isNegative()) {
            throw new IllegalArgumentException("Alter kann nicht negativ sein");
        }

        if (alter.getYears() >= 18) {
            return Alter.O18;
        } else if (alter.getYears() >= 16) {
            return Alter.U18;
        } else if (alter.getYears() >= 0) {
            return Alter.U16;
        }
        throw new IllegalArgumentException("Alter muss erreichbar sein.");
    }

}
