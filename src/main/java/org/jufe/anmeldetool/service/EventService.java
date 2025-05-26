package org.jufe.anmeldetool.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Getter
@RequiredArgsConstructor
@Service
public class EventService extends BaseService<Event> {

    private final EventRepository repository;

    private final ShuttleService shuttleService;

    @Autowired
    public EventService(EventRepository repository, ShuttleService shuttleService) {
        super(repository);
        this.repository = repository;
        this.shuttleService = shuttleService;
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
                       .build();
        Shuttle shuttle1hin = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle1rueck = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle2hin = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle2rueck = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle3hin = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle3rueck = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle4hin = shuttleService.createShuttle(e, null, "");
        Shuttle shuttle4rueck = shuttleService.createShuttle(e, null, "");

        Set<Halt> halt1hin = new HashSet<>();
        Set<Halt> halt1rueck = new HashSet<>();
        Set<Halt> halt2hin = new HashSet<>();
        Set<Halt> halt2rueck = new HashSet<>();
        Set<Halt> halt3hin = new HashSet<>();
        Set<Halt> halt3rueck = new HashSet<>();
        Set<Halt> halt4hin = new HashSet<>();
        Set<Halt> halt4rueck = new HashSet<>();

        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(10, 30), 27.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(11, 30), 22.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(12, 45), 20, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(13, 30), 17.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 45), 15, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(16, 45), 10, shuttle1hin));

        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(18, 45), 27.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(17, 45), 22.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(16, 30), 20, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(15, 45), 17.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 30), 15, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(12, 30), 10, shuttle1rueck));

        halt2hin.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(10, 30), 27.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(11, 30), 22.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(12, 45), 20, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(13, 30), 17.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 45), 15, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(16, 45), 10, shuttle2hin));

        halt2rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(18, 45), 27.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(17, 45), 22.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(16, 30), 20, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(15, 45), 17.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 30), 15, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(12, 30), 10, shuttle2rueck));

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
