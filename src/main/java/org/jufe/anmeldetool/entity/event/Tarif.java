package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    private LocalDate bis;
    private double preis;

    @ManyToOne
    private Event event;
}
