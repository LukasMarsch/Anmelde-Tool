package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Tarif extends BaseEntity implements Serializable {

    private final LocalDate bis;

    private final double preis;

    @ManyToOne
    private Event event;

}
