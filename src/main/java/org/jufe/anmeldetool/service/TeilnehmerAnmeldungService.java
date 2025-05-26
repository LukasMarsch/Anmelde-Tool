package org.jufe.anmeldetool.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.jufe.anmeldetool.repository.anmeldung.TeilnehmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeilnehmerAnmeldungService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AnmeldungRepository anmeldungRepository;

    private final TeilnehmerRepository teilnehmerRepository;

    @Transactional
    public void create(Teilnehmer teilnehmer, UUID id) throws EntityNotFoundException {
        Anmeldung anmeldung = anmeldungRepository.getReferenceById(id);
        anmeldung.setTeilnehmer(teilnehmer);
        teilnehmer.setAnmeldung(anmeldung);
        teilnehmerRepository.save(teilnehmer);
        LOGGER.debug(teilnehmer.getId());
        anmeldungRepository.save(anmeldung);
        LOGGER.debug(anmeldung);
    }

}
