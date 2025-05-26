package org.jufe.anmeldetool.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.repository.anmeldung.AnmeldungRepository;
import org.springframework.stereotype.Service;

@Getter
@RequiredArgsConstructor
@Service
public class AnmeldungService {

    private final AnmeldungRepository anmeldungRepository;

}
