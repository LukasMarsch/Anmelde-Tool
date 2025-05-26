package org.jufe.anmeldetool.entity.reise;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;
import org.jufe.anmeldetool.entity.event.Event;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Shuttle extends BaseEntity implements Serializable {

    @ManyToOne
    private Event event;

    @OneToMany(mappedBy = "shuttle")
    private Set<Halt> haltestellen;

    private String detail;

}
