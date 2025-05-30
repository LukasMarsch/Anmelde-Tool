package org.jufe.anmeldetool.entity.reise;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Halt extends BaseEntity implements Serializable {

    private PostAdresse ort;

    private LocalTime zeitpunkt;

    private double preis;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shuttle shuttle;

    @Override
    public String toString() {
        // preis wird als Dezimal mit mindestens 3 Stellen insgesamt, davon 2 nach dem Lokalen Dezimalseparator geschrieben
        var dtf = DateTimeFormatter.ofPattern("HH:mm");
        return "%s %s: %,3.2f€".formatted(this.ort, dtf.format(this.zeitpunkt), this.preis);
    }

}
