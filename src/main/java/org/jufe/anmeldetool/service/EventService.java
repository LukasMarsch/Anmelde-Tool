package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.event.Tarif;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EventService extends BaseService<Event> {

    private final EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Cacheable
    public Event getNextEvent() {
        Event e = repository.findFirstByOrderByVonDesc();
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
                       .tarif(Set.of(new Tarif(LocalDate.of(2000, 1, 1), 25.0D)))
                       .build();
        repo.save(e);
        return e;
    }

    public List<Event> getAllEvents() {
        List<Event> events = repository.findAll();
        for (Event event : events) {
            event.berechneTeilnehmerStatistik();
        }
        return events;
    }

    public Optional<Event> getEventById(@NonNull UUID eventId) {
        Optional<Event> event = repository.findById(eventId);
        event.ifPresent(Event::berechneTeilnehmerStatistik);
        return event;
    }

}
