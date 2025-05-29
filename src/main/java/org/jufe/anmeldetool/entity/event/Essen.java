package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
public class Essen extends BaseEntity implements Serializable {

    @ManyToOne(optional = false)
    private final Event event;

    private LocalDate tag;

    private Mahlzeit mahlzeit;

}
