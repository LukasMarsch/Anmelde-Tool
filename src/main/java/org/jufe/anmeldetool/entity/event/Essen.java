package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Essen extends BaseEntity implements Serializable {

    private LocalDate tag;

    private Mahlzeit mahlzeit;

}
