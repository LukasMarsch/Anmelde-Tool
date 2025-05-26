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

        var heskem = new PostAdresse("L3125", "Busbahnhof", "35085", "Heskem");
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

        halt3hin.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(9, 30), 32.5d, shuttle3hin));
        halt3hin.add(
                new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(12, 00), 27.5d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(13, 00), 25d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(14, 15), 22.5d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(15, 15), 20d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(16, 15), 15d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(16, 45), 10d, shuttle3hin));

        halt3rueck.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(12, 30), 10d, shuttle3rueck));
        halt3hin.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(13, 00), 15d, shuttle3rueck));
        halt3hin.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(14, 00), 20d, shuttle3rueck));
        halt3hin.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(15, 00), 22.5d, shuttle3rueck));
        halt3hin.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(16, 15), 25d, shuttle3rueck));
        halt3hin.add(
                new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(17, 15), 27.5d, shuttle3rueck));
        halt3hin.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(19, 45), 32.5d, shuttle3rueck));

        halt4hin.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(7, 45), 32.5d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(9, 30), 30d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(10, 00), 25d,
                shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("(TotalEnergies Autothof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(11, 00), 22.5d,
                shuttle4hin));
        halt4hin.add(
                new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(11, 45), 17.5d,
                        shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 00), 15d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(15, 15), 12.5d, shuttle4hin));

        halt4rueck.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(12, 00), 12.5d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 30), 15d, shuttle4rueck));
        halt4rueck.add(
                new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(17, 30), 17.5d,
                        shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("(TotalEnergies Autohof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(18, 15), 22.5d,
                shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(19, 15), 32.5d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(19, 45), 30d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(21, 30), 25d,
                shuttle4rueck));

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
