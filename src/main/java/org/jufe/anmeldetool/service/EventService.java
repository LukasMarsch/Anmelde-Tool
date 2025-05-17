package org.jufe.anmeldetool.service;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.event.Tarif;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event getNextEvent() {
        return Event.builder()
                    .von(LocalDate.of(2029, 1, 1))
                    .name("JuFe '29")
                    .bis(LocalDate.of(2029, 1, 4))
                    .tarif(Set.of(new Tarif(LocalDate.of(2025, 1, 1), 30.0D)))
                    .build();
    }

    @Transactional
    public void addAnmeldungToEventById(@NonNull UUID event, @NonNull Anmeldung anmeldung) {
        eventRepository.getReferenceById(event)
                       .addAnmeldung(anmeldung);
    }


    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            event.berechneTeilnehmerStatistik();
        }
        return events;
    }

    public Optional<Event> getEventById(@NonNull UUID eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        event.ifPresent(Event::berechneTeilnehmerStatistik);
        return event;
    }
}
