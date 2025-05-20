package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return repository.findFirstByOrderByVonDesc();
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
