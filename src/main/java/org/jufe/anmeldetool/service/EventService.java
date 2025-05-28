package org.jufe.anmeldetool.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.repository.reise.HaltRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

        Shuttle shuttle1 = shuttleService.createEmptyShuttle(e);
        Shuttle shuttle2 = shuttleService.createEmptyShuttle(e);
        Shuttle shuttle3 = shuttleService.createEmptyShuttle(e);
        Shuttle shuttle4 = shuttleService.createEmptyShuttle(e);

        e.addShuttle(shuttle1);
        e.addShuttle(shuttle2);
        e.addShuttle(shuttle3);
        e.addShuttle(shuttle4);

        Set<Halt> halt1hin = new HashSet<>();
        Set<Halt> halt1rueck = new HashSet<>();
        Set<Halt> halt2hin = new HashSet<>();
        Set<Halt> halt2rueck = new HashSet<>();
        Set<Halt> halt3hin = new HashSet<>();
        Set<Halt> halt3rueck = new HashSet<>();
        Set<Halt> halt4hin = new HashSet<>();
        Set<Halt> halt4rueck = new HashSet<>();

        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(10, 30), 27.5, shuttle1));
        halt1hin.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(11, 30), 22.5, shuttle1));
        halt1hin.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(12, 45), 20, shuttle1));
        halt1hin.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(13, 30), 17.5, shuttle1));
        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 45), 15, shuttle1));
        halt1hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(16, 45), 10, shuttle1));
        shuttle1.setHinHaltestellen(halt1hin);

        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(18, 45), 27.5, shuttle1));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(17, 45), 22.5, shuttle1));
        halt1rueck.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(16, 30), 20, shuttle1));
        halt1rueck.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(15, 45), 17.5, shuttle1));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 30), 15, shuttle1));
        halt1rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(12, 30), 10, shuttle1));
        shuttle1.setRueckHaltestellen(halt1rueck);
        shuttleService.save(shuttle1);

        halt2hin.add(new Halt(new PostAdresse("Mühlendamm", "3", "24114", "Kiel"), LocalTime.of(9, 0), 35, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Wasbecker Straße", "330", "24537", "Neumünster"), LocalTime.of(9, 30), 30, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Paul-Ehrlich-Straße", "", "22763", "Hamburg"), LocalTime.of(10, 30), 27.5, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Rahrsberg", "7", "29614", "Soltau"), LocalTime.of(11, 15), 25, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Busbahnhof Celle", "", "29221", "Celle"), LocalTime.of(12, 0), 22.5, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("U-Bahn Hannover Zoo", "", "30175", "Hannover"), LocalTime.of(12, 45), 20, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Bahnhof Hildesheim", "", "31134", "Hildesheim"), LocalTime.of(13, 15), 17.5, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Busbahnhof Göttingen", "", "37073", "Göttingen"), LocalTime.of(14, 15), 15, shuttle2));
        halt2hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(15, 45), 10, shuttle2));
        shuttle2.setHinHaltestellen(halt2hin);

        halt2rueck.add(new Halt(new PostAdresse("Mühlendamm", "3", "24114", "Kiel"), LocalTime.of(21, 15), 35, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Wasbecker Straße", "330", "24537", "Neumünster"), LocalTime.of(20, 45), 30, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Paul-Ehrlich-Straße", "", "22763", "Hamburg"), LocalTime.of(19, 45), 27.5, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Rahrsberg", "7", "29614", "Soltau"), LocalTime.of(19, 0), 25, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Busbahnhof Celle", "", "29221", "Celle"), LocalTime.of(18, 15), 22.5, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("U-Bahn Hannover Zoo", "", "30175", "Hannover"), LocalTime.of(17, 30), 20, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Bahnhof Hildesheim", "", "31134", "Hildesheim"), LocalTime.of(16, 0), 17.5, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Busbahnhof Göttingen", "", "37073", "Göttingen"), LocalTime.of(15, 0), 15, shuttle2));
        halt2rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(13, 30), 10, shuttle2));
        shuttle2.setRueckHaltestellen(halt2rueck);
        shuttleService.save(shuttle2);

        halt3hin.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(9, 30), 32.5d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(12, 0), 27.5d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(13, 0), 25d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(14, 15), 22.5d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(15, 15), 20d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(16, 15), 15d, shuttle3));
        halt3hin.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(16, 45), 10d, shuttle3));
        shuttle3.setHinHaltestellen(halt3hin);

        halt3rueck.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(12, 30), 10d, shuttle3));
        halt3rueck.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(13, 0), 15d, shuttle3));
        halt3rueck.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(14, 0), 20d, shuttle3));
        halt3rueck.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(15, 0), 22.5d, shuttle3));
        halt3rueck.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(16, 15), 25d, shuttle3));
        halt3rueck.add(
                new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(17, 15), 27.5d, shuttle3));
        halt3rueck.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(19, 45), 32.5d, shuttle3));
        shuttle3.setRueckHaltestellen(halt3rueck);
        shuttleService.save(shuttle3);

        halt4hin.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(7, 45), 32.5d, shuttle4));
        halt4hin.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(9, 30), 30d, shuttle4));
        halt4hin.add(
                new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(10, 0), 25d, shuttle4));
        halt4hin.add(new Halt(new PostAdresse("(TotalEnergies Autothof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(11, 0), 22.5d,
                shuttle4));
        halt4hin.add(
                new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(11, 45), 17.5d,
                        shuttle4));
        halt4hin.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 0), 15d, shuttle4));
        halt4hin.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(15, 15), 12.5d, shuttle4));
        shuttle4.setHinHaltestellen(halt4hin);

        halt4rueck.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(12, 0), 12.5d, shuttle4));
        halt4rueck.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 30), 15d, shuttle4));
        halt4rueck.add(
                new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(17, 30), 17.5d,
                        shuttle4));
        halt4rueck.add(new Halt(new PostAdresse("(TotalEnergies Autohof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(18, 15), 22.5d,
                shuttle4));
        halt4rueck.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(19, 15), 32.5d, shuttle4));
        halt4rueck.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(19, 45), 30d, shuttle4));
        halt4rueck.add(
                new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(21, 30), 25d, shuttle4));
        shuttle4.setRueckHaltestellen(halt4rueck);
        shuttleService.save(shuttle4);

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
