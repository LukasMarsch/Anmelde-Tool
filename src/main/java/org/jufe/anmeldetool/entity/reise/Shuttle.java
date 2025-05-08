package org.jufe.anmeldetool.entity.reise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jufe.anmeldetool.entity.event.Event;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Shuttle {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private UUID id;

     @ManyToOne
     private Event event;

     @OneToMany(mappedBy = "shuttle")
     private List<Halt> haltestellen;
     private String detail;
}
