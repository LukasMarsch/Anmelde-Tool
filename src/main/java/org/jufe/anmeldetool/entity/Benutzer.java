package org.jufe.anmeldetool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Benutzer {
    private String name;
    private String hash;
    private LocalDate von;
    private LocalDate bis;
    private List<Event> organisiert;
    private boolean administrator;

    public void addEvent(Event e) {
        //TODO implement  - String als return???
    }

    public void removeEvent(Event e) {
        //TODO implement
    }
}
