package org.jufe.anmeldetool.entity.anmeldung;

import io.micrometer.common.lang.NonNull;
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
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Anmeldung extends BaseEntity implements Serializable {

    @ManyToOne
    @org.springframework.lang.NonNull
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

    public Optional<byte[]> getEinverstaendnisErklaerung() {
        return null != einverstaendnisErklaerung ? Optional.of(einverstaendnisErklaerung) : Optional.empty();
    }

    protected void setEinverstaendnisErklaerung(byte[] einverstaendnisErklaerung) {
        this.einverstaendnisErklaerung = einverstaendnisErklaerung;
    }

    public void setEinverstaendnisErklaerung(Optional<byte[]> einverstaendnisErklaerung) {
        einverstaendnisErklaerung.ifPresent(this::setEinverstaendnisErklaerung);
    }

    public Optional<Halt> getNimmtShuttleVon() {
        return this.nimmtShuttleVon != null ? Optional.of(this.nimmtShuttleVon) : Optional.empty();
    }

    public void setNimmtShuttleVon(@NonNull Optional<Halt> nimmtShuttleVon) throws NullPointerException {
        nimmtShuttleVon.ifPresent(this::setNimmtShuttleVon);
    }

    public void setNimmtShuttleVon(@NonNull Halt nimmtShuttleVon) {
        this.nimmtShuttleVon = nimmtShuttleVon;
    }

}
