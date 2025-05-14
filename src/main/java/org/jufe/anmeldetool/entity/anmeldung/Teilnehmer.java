package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.time.Period;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teilnehmer extends BaseEntity {

    @OneToOne
    private Anmeldung anmeldung;

    private Boolean angekommen;

    private boolean anwesend;

    private boolean bestaetigungVersendet;

    @Enumerated(EnumType.STRING)
    private Rolle rolle;

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
