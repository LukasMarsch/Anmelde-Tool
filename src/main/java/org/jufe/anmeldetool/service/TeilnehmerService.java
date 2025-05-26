package org.jufe.anmeldetool.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jufe.Result;
import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.jufe.anmeldetool.repository.anmeldung.TeilnehmerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
@Service
public class TeilnehmerService {

    private final TeilnehmerRepository repository;

    public Result<Teilnehmer> toTeilnehmer(Teilnehmer teilnehmer) {
        Optional<Teilnehmer> teilOpt;
        try {
            teilOpt = repository.findById(teilnehmer.getId());
        } catch (Exception e) {
            teilOpt = Optional.empty();
        }
        Result<Teilnehmer> r;
        if (teilOpt.isEmpty()) {
            teilnehmer.setAngekommen(false);
            teilnehmer.setAnwesend(false);
            teilnehmer.setRolle(null);
            repository.save(teilnehmer);
            r = Result.of(teilnehmer);
        } else {
            r = Result.of(new Exception("Teilnahme liegt schon vor"));
        }
        return r;
    }

}
