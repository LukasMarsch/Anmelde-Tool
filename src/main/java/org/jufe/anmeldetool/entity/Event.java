package org.jufe.anmeldetool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String name;
    private LocalDate von;
    private LocalDate bis;
    private Essen erstesEssen;
    private Essen letztesEssen;
    private boolean mitKaffee;
    private Benutzer creator;
    private List<Benutzer> organisatoren;
    private List<Anmeldung> anmeldungen;
    private Optional<Shuttle> shuttle;
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
}
