package org.jufe.anmeldetool.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Essen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    private LocalDate tag;
    private Mahlzeit mahlzeit;
}
