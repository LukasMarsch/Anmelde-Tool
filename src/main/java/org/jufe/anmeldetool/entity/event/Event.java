package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.*;
import lombok.*;
import org.jufe.anmeldetool.entity.BaseEntity;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.wrapper.TeilnehmerStatistik;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EVENTTABLE")
@Builder
public class Event extends BaseEntity implements Serializable {

    private String name;

    private LocalDate von;

    private LocalDate bis;

    @Enumerated(EnumType.STRING)
    private Mahlzeit erstesMahlzeit;

    @Enumerated(EnumType.STRING)
    private Mahlzeit letzteMahlzeit;

    @OneToMany(mappedBy = "event")
    @OrderBy("tag, mahlzeit")
    private Set<Essen> essen;

    private boolean mitKaffee;

    @ManyToOne
    private Benutzer creator;

    @ManyToMany
    @OrderBy("name")
    private Set<Benutzer> organisatoren;

    @OneToMany(mappedBy = "event")
    @OrderBy("nachname, vorname")
    private Set<Anmeldung> anmeldungen;

    @OneToMany(mappedBy = "event")
    @OrderBy("id")
    private Set<Shuttle> shuttles;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    @OrderBy("bis")
    private Set<Tarif> tarif;

    @Transient
    private TeilnehmerStatistik statistik;

    public void addOrganisator(Benutzer benutzer) {
        organisatoren.add(benutzer);
    }

    public void removeOrganisator(Benutzer benutzer) {
        organisatoren.remove(benutzer);
    }

    public void addAnmeldung(Anmeldung anmeldung) {
        anmeldungen.add(anmeldung);
    }

    public void removeAnmeldung(Anmeldung anmeldung) {
        anmeldungen.remove(anmeldung);
    }

    public void addShuttle(Shuttle shuttle) {
        this.shuttles.add(shuttle);
    }

    public void removeShuttle(Shuttle shuttle) {
        this.shuttles.remove(shuttle);
    }

    public void berechneTeilnehmerStatistik() {
        statistik = TeilnehmerStatistik.berechne(anmeldungen);
    }
}
