package org.jufe.anmeldetool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Essen {
    private LocalDate tag;
    private Mahlzeit mahlzeit;
}
