package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Benutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

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
