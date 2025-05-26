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

import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teilnehmer extends BaseEntity implements Serializable {

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

}
