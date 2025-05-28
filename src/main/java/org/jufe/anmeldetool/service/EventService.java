package org.jufe.anmeldetool.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.event.Mahlzeit;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.repository.reise.HaltRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final ShuttleService shuttleService;

    private final HaltRepository haltRepository;

    @Cacheable
    public Event getNextEvent() {
        Event e = eventRepository.findFirstByOrderByVonDesc();
        if (e == null) {
            e = init();
        }
        return e;
    }

    private Event init() {
        Event e = Event.builder()
                       .name("JuFe 2018")
                       .von(LocalDate.of(2018, 1, 1))
                       .bis(LocalDate.of(2018, 1, 3))
                       .shuttles(new HashSet<>())
                       .ersteMahlzeit(Mahlzeit.ABEND)
                       .letzteMahlzeit(Mahlzeit.MITTAG)
                       .build();

        eventRepository.save(e);
        shuttleService.shuttleServiceinit(e);
        return e;
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
