package org.jufe.anmeldetool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class Halt {
    private PostAdresse ort;
    private LocalTime zeit;
    private double preis;
}
