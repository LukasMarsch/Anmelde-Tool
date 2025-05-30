package org.jufe.anmeldetool.entity.anmeldung;

public enum Rolle {
    /* @formatter:off */
    TEILNEHMER("Teilnehmer"),
    WORKSHOPLEITER("Workshopleiter"),
    WORKSHOPLEITER_MULTI("Workshopleiter meherere"),
    KUECHENTEAM("Küchenteam"),
    BANDMITGLIED("Bandmitglied"),
    HELFER("Helfer"),
    JUFETEAM("JuFeTeam");
    /* @formatter:on */

    private static String displayValue;

    Rolle(String displayValue) {}

    public float getRabatt() {
        return switch (this) {
            case TEILNEHMER -> 1F;
            case WORKSHOPLEITER -> 0.5F;
            case WORKSHOPLEITER_MULTI -> 0F;
            case KUECHENTEAM -> 0.5F;
            case BANDMITGLIED -> 0.5F;
            case HELFER -> 1F;
            case JUFETEAM -> 0F;
        };
    }
}
