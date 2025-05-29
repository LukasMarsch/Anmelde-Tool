package org.jufe.anmeldetool.entity.event;

public enum Mahlzeit {
    FRUEH("Frühstück"),
    MITTAG("Mittagessen"),
    KAFFEE("Kaffeetrinken"),
    ABEND("Abendbrot");

    private final String displayValue;
    private Mahlzeit(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}
