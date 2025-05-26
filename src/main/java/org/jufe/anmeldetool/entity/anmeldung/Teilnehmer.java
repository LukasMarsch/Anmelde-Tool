package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.Period;

@Getter
@Setter
@Entity
public class Teilnehmer extends BaseEntity implements Serializable {

    @OneToOne(orphanRemoval = false, optional = false, fetch = FetchType.LAZY)
    private Anmeldung anmeldung;

    private boolean angekommen;

    private boolean anwesend;

    private boolean bestaetigungVersendet;

    @Enumerated(EnumType.STRING)
    private Rolle rolle;

    public Teilnehmer() {
        this.anmeldung = null;
        this.angekommen = false;
        this.anwesend = false;
        this.bestaetigungVersendet = false;
    }

    public Teilnehmer(Anmeldung anmeldung) {
        this.anmeldung = anmeldung;
        this.angekommen = false;
        this.anwesend = false;
        this.bestaetigungVersendet = false;
    }

    public Alter alter() throws IllegalArgumentException {
        Period alter = anmeldung.getGeburtstag()
                                .until(anmeldung.getEvent()
                                                .getVon());
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
