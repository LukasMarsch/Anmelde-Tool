package org.jufe.anmeldetool.service;

import org.jufe.Result.Result;
import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.jufe.anmeldetool.repository.anmeldung.TeilnehmerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeilnehmerService extends BaseService<Teilnehmer> {

    TeilnehmerRepository repository;

    public TeilnehmerService(TeilnehmerRepository repository) {
        super(repository);
        this.repository = repository;
    }

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
            repo.save(teilnehmer);
            r = Result.of(teilnehmer);
        } else {
            r = Result.of(new Exception("Teilnahme liegt schon vor"));
        }
        return r;
    }

}
