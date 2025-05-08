package org.jufe.anmeldetool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Entschuldigung {
    private String name;
    private PostAdresse anschrift;
    private String klasse;
}
