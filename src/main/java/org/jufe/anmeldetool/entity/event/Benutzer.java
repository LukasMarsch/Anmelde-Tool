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
import java.util.Set;

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
    private Set<Event> organisiert;

    @OneToMany(mappedBy = "creator")
    private Set<Event> leitet;

    private boolean administrator;

    public void addOrganisiert(Event e) {
        organisiert.add(e);
    }

    public void removeOrganisiert(Event e) {
        organisiert.remove(e);
    }

    public void addLeitet(Event e) {
        leitet.add(e);
    }

    public void removeLeitet(Event e) {
        leitet.remove(e);
    }

}
