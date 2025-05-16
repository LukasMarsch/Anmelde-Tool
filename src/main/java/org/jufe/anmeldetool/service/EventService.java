package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

}
