package org.jufe.anmeldetool.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TeilnehmerStatistik implements Serializable {

    private int angemeldet;

    private int teilnehmer;

    private int erwartet;

    private int angekommen;

    private int anwesend;

    private int abwesend;

    public static TeilnehmerStatistik berechne(Set<Anmeldung> anmeldungen) {
        int[] stats = new int[] {0, 0, 0, 0, 0, 0}; // repräsentiert die sechs Metriken
        for (Anmeldung anmeldung : anmeldungen) {
            stats[0]++;
            if (anmeldung.isBestaetigt())
                stats[1]++; // Anmeldung durch Bezahlung bestätigt -> Teilnehmer

            if (anmeldung.isAngekommen()) {
                stats[3]++; // Teilnehmer auf Grundstück angekommen
                if (anmeldung.isAnwesend())
                    stats[4]++; // Teilnehmer MOMENTAN auf Grundstück
            }

        }
        stats[2] = stats[1] - stats[3]; // Erwartet = Teilnehmer - Angekommen
        stats[5] = stats[3] - stats[4]; // Abwesend = Angekommen - Anwesend

        // nicht schön, aber löppt
        return new TeilnehmerStatistik(stats[0], stats[1], stats[2], stats[3], stats[4], stats[5]);
    }

}
