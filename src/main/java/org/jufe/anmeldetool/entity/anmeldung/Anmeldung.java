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
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Anmeldung extends BaseEntity implements Serializable {

    @ManyToOne
    @NonNull
    private Event event;

    private String vorname;

    private String nachname;

    private InternetAddress mail;

    @OneToOne
    @Nullable
    private Teilnehmer teilnehmer;

    private PostAdresse adresse;

    private Kirchenbezirk bezirk;

    private String gemeinde;

    private LocalDate geburtstag;

    @Enumerated(EnumType.STRING)
    private Geschlecht geschlecht;

    @Enumerated(EnumType.STRING)
    private Isst isst;

    @ManyToMany
    private Set<Essen> anwesend;

    private boolean schwimmer;

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

    public Anmeldung(Event event) {
        this.event = event;
    }

    public void addEssen(Essen essen) {
        anwesend.add(essen);
    }

    public void removeEssen(Essen essen) {
        anwesend.remove(essen);
    }

}
