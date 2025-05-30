package org.jufe.anmeldetool.entity.anmeldung;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jufe.anmeldetool.entity.BaseEntity;
import org.jufe.anmeldetool.entity.event.Event;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abwesend extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    private Anmeldung anmeldung;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    private LocalDate tag;

    @Enumerated(EnumType.STRING)
    private Mahlzeit mahlzeit;

}
