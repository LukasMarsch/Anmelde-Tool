package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.mail.internet.InternetAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.entschuldigung.Entschuldigung;
import org.jufe.anmeldetool.entity.event.Essen;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Anmeldung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    private Event event;
    private String vorname;
    private String nachname;
    private InternetAddress mail;
    private PostAdresse adresse;
    private Kirchenbezirk bezirk;
    private String gemeinde;
    private LocalDate geburtstag;
    @Enumerated
    private Geschlecht geschlecht;
    private Isst isst;

    @ManyToMany
    private List<Essen> anwesend;
    private boolean schwimmer;
    private String anmerkung;
    private byte[] einverstaendnisErklaerung;
    private String nameNotfallkontakt;
    private String nummerNutfallkontakt;

    @ManyToOne
    private Halt nimmtShuttleVon;

    @OneToOne
    private Entschuldigung entschuldigung;

    public void addEssen(Essen essen) {
        //TODO implement
    }

    public void removeEssen(Essen essen) {
        // TODO implement
    }

    public Optional<byte[]> getEinverstaendnisErklaerung() {
        return null != einverstaendnisErklaerung
                ? Optional.of(einverstaendnisErklaerung)
                : Optional.empty();
    }

    public void setEinverstaendnisErklaerung(byte[] einverstaendnisErklaerung) {
        this.einverstaendnisErklaerung = einverstaendnisErklaerung;
    }

    public void setEinverstaendnisErklaerung(Optional<byte[]> einverstaendnisErklaerung) {
        einverstaendnisErklaerung.ifPresent(this::setEinverstaendnisErklaerung);
    }

    public Optional<Halt> getNimmtShuttleVon() {
        return this.nimmtShuttleVon != null
                ? Optional.of(this.nimmtShuttleVon)
                : Optional.empty();
    }

    public void setNimmtShuttleVon(Optional<Halt> nimmtShuttleVon) {
        nimmtShuttleVon.ifPresent(this::setNimmtShuttleVon);
    }

    public void setNimmtShuttleVon(Halt nimmtShuttleVon) {
        this.nimmtShuttleVon = nimmtShuttleVon;
    }
}
