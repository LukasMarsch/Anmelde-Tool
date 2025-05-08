package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.reise.Shuttle;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

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
    private List<Benutzer> organisatoren;

    @OneToMany(mappedBy = "event")
    private List<Anmeldung> anmeldungen;

    @OneToMany(mappedBy = "event")
    private List<Shuttle> shuttles;

    @OneToMany(mappedBy = "event")
    private List<Tarif> tarif;

    public void addBenutzer(Benutzer benutzer) {
        //TODO String als return ??? implement
    }

    public void removeBenutzer(Benutzer benutzer) {
        //TODO String als return??? implement
    }

    public void addAnmeldung(Anmeldung anmeldung) {
        //TODO String als return??? implement
    }

    public void removeAnmeldung(Anmeldung anmeldung) {
        //TODO String als return??? implement
    }

    public void addShuttle(Shuttle shuttle) {
        this.shuttles.add(shuttle);
    }

    public void removeShuttle(Shuttle shuttle) {
        this.shuttles.remove(shuttle);
    }
}
