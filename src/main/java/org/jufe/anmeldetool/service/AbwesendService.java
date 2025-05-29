package org.jufe.anmeldetool.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Abwesend;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.anmeldung.Mahlzeit;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.anmeldung.AbwesendRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AbwesendService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AbwesendRepository abwesendRepository;

    public Abwesend create(Anmeldung anmeldung, LocalDate tag, Mahlzeit mahlzeit) throws IllegalArgumentException, OptimisticLockingFailureException {
        Event e = anmeldung.getEvent();
        Abwesend abwesend = new Abwesend(anmeldung, e, tag, mahlzeit);
        LOGGER.info(() -> "Creating %s".formatted(abwesend));
        return abwesendRepository.save(abwesend);
    }

}
