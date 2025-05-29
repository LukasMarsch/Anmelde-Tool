package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class Tarif extends BaseEntity implements Serializable {

    private LocalDate bis;

    private double preis;

    @ManyToOne
    private Event event;

}
