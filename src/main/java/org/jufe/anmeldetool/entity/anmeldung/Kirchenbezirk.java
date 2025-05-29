package org.jufe.anmeldetool.entity.anmeldung;

public enum Kirchenbezirk {
    NONE("-"),
    BERLIN_BRANDENBURG("Berlin-Brandenburg"),
    HESSEN_NORD("Hessen-Nord"),
    HESSEN_SUED("Hessen-Süd"),
    LAUSITZ("Lausitz"),
    NIEDERSACHSEN_OST("Niedersachsen-Ost"),
    NIEDERSACHSEN_WEST("Niedersachsen-West"),
    NIEDERSACHSEN_SUED("Niedersachsen-Süd"),
    RHEINLAND_WESTPFALEN("Rheinland-Westpfalen"),
    SACHSEN_THUERINGEN("Sachsen-Thüringen"),
    SUEDDEUTSCHLAND("Süddeutschland"),
    ELKIB("ELKiB");

    private final String displayValue;
    private Kirchenbezirk(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }
}
