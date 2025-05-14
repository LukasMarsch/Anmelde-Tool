package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teilnehmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

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
