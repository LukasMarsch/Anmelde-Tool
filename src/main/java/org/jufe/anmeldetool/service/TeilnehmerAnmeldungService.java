package org.jufe.anmeldetool.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    private final AnmeldungRepository anmeldungRepository;

    private final TeilnehmerRepository teilnehmerRepository;

    @Transactional
    public void create(Teilnehmer teilnehmer, UUID id) throws EntityNotFoundException {
        Anmeldung anmeldung = anmeldungRepository.getReferenceById(id);
        anmeldung.setTeilnehmer(teilnehmer);
        teilnehmer.setAnmeldung(anmeldung);
        anmeldungRepository.save(anmeldung);
        teilnehmerRepository.save(teilnehmer);
    }

}
