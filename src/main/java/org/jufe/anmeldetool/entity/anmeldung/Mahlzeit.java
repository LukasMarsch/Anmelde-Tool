package org.jufe.anmeldetool.entity.anmeldung;

import lombok.Getter;

public enum Mahlzeit {
    FRUEH("Frühstück"), MITTAG("Mittagessen"), KAFFEE("Kaffeetrinken"), ABEND("Abendbrot");

    @Getter
    private final String displayValue;

    Mahlzeit(String displayValue) {
        this.displayValue = displayValue;
    }
}
