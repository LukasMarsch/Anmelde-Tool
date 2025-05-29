package org.jufe.anmeldetool.entity.anmeldung;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public final class MahlzeitConverter implements Converter<String, ImmutablePair<LocalDate, Mahlzeit>> {

    @Override
    public ImmutablePair<LocalDate, Mahlzeit> convert(String source) {
        String[] values = source.split("\\.");
        LocalDate l;
        Mahlzeit m;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            TemporalAccessor ta = df.parse(values[0]);
            l = LocalDate.of(ta.get(ChronoField.YEAR), ta.get(ChronoField.MONTH_OF_YEAR), ta.get(ChronoField.DAY_OF_MONTH));
            m = Mahlzeit.valueOf(values[2]);
        } catch (Exception e) {
            return null;
        }
        return new ImmutablePair<>(l, m);
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super ImmutablePair<LocalDate, Mahlzeit>, ? extends U> after) {
        return Converter.super.andThen(after);
    }

}
