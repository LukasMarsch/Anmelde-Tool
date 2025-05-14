package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Benutzer extends BaseEntity implements Serializable {

    private String name;

    private String hash;

    private LocalDate von;

    private LocalDate bis;

    @ManyToMany()
    private List<Event> organisiert;

    @OneToMany(mappedBy = "creator")
    private List<Event> leitet;

    private boolean administrator;

    public void addEvent(Event e) {
        //TODO implement  - String als return???
    }

    public void removeEvent(Event e) {
        //TODO implement
    }

}
