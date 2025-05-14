package org.jufe.anmeldetool.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event getNextEvent() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }

    @Transactional
    public void addAnmeldungToEventById(@NonNull UUID event, @NonNull Anmeldung anmeldung) {
        eventRepository.getReferenceById(event)
                       .addAnmeldung(anmeldung);
    }

}
