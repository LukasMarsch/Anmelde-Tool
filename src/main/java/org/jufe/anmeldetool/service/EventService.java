package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.event.Tarif;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class EventService extends BaseService<Event> {

    @Autowired
    public EventService(EventRepository repo) {
        super(repo);
    }

    public Event getNextEvent() {
        return Event.builder()
                    .von(LocalDate.of(2029, 1, 1))
                    .name("JuFe '29")
                    .bis(LocalDate.of(2029, 1, 4))
                    .tarif(Set.of(new Tarif(LocalDate.of(2025, 1, 1), 30.0D)))
                    .build();
    }

}
