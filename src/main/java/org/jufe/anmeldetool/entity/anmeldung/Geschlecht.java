package org.jufe.anmeldetool.entity.anmeldung;

public enum Geschlecht {
    M("männlich"),
    W("weiblich"),
    D("divers");

    private final String displayValue;
    private Geschlecht(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }
}
