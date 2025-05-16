package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.*;
import lombok.*;
import org.jufe.anmeldetool.entity.BaseEntity;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.reise.Shuttle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "EVENTTABLE")
@Builder
public class Event extends BaseEntity implements Serializable {

    private String name;

    private LocalDate von;

    private LocalDate bis;

    @ManyToOne
    private Essen erstesEssen;

    @ManyToOne
    private Essen letztesEssen;

    private boolean mitKaffee;

    @ManyToOne
    private Benutzer creator;

    @ManyToMany
    private Set<Benutzer> organisatoren;

    @OneToMany(mappedBy = "event")
    private Set<Anmeldung> anmeldungen;

    @OneToMany(mappedBy = "event")
    private Set<Shuttle> shuttles;

    @OneToMany(mappedBy = "event")
    private Set<Tarif> tarif;

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

    @Override
    public String toString() {
        return "Event [name=" + name + ", von=" + von + ", bis=" + bis;
    }
}
