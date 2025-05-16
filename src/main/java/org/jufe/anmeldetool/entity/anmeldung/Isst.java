package org.jufe.anmeldetool.entity.anmeldung;

public enum Isst {
    VEGAN("vegan"),
    VEGETARISCH("vegetarisch"),
    OMNIVOR("alles");

    private final String displayValue;
    private Isst(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }

}
