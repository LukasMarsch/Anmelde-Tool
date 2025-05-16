package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnmeldungService extends BaseService<Anmeldung> {

    @Autowired
    public AnmeldungService(AnmeldungRepository repo) {
        super(repo);
    }

}
