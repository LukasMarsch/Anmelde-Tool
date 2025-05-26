package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.Period;
import java.util.Objects;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Teilnehmer extends BaseEntity implements Serializable {

    @OneToOne(orphanRemoval = false, optional = false, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Anmeldung anmeldung;

    private Boolean angekommen;

    private Boolean anwesend;

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

    public Alter alter() throws IllegalArgumentException, NullPointerException {
        Period alter = Objects.requireNonNull(anmeldung)
                              .getGeburtstag()
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
        throw new IllegalArgumentException("Alter muss vorhanden sein.");
    }

}
