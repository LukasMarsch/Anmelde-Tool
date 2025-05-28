package org.jufe.anmeldetool.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.repository.event.EventRepository;
import org.jufe.anmeldetool.repository.reise.ShuttleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShuttleService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final ShuttleRepository shuttleRepository;

    private final EventRepository eventRepository;

    public Shuttle createEmptyShuttle(LocalDate datum, Event e) {
        return shuttleRepository.save(new Shuttle(datum, e, new HashSet<>(), ""));
    }

    public Set<Halt> getAllHaltestellenAmTag(LocalDate datum) {
        Set<Shuttle> shuttleList = shuttleRepository.findAllByDatumOrderById(datum);
        Set<Halt> alleHaltestellen = new LinkedHashSet<>(32, 0.7f);
        for (Shuttle shuttle : shuttleList) {
            alleHaltestellen.addAll(shuttle.getHaltestellen());
        }
        LOGGER.trace(() -> alleHaltestellen);
        return alleHaltestellen;
    }

    void shuttleServiceinit(Event e) {
        Shuttle shuttle1hin = createEmptyShuttle(e.getVon(), e);
        Shuttle shuttle2hin = createEmptyShuttle(e.getVon(), e);
        Shuttle shuttle3hin = createEmptyShuttle(e.getVon(), e);
        Shuttle shuttle4hin = createEmptyShuttle(e.getVon(), e);
        Shuttle shuttle1rueck = createEmptyShuttle(e.getBis(), e);
        Shuttle shuttle2rueck = createEmptyShuttle(e.getBis(), e);
        Shuttle shuttle3rueck = createEmptyShuttle(e.getBis(), e);
        Shuttle shuttle4rueck = createEmptyShuttle(e.getBis(), e);

        e.addShuttle(shuttle1hin);
        e.addShuttle(shuttle2hin);
        e.addShuttle(shuttle3hin);
        e.addShuttle(shuttle4hin);
        e.addShuttle(shuttle1rueck);
        e.addShuttle(shuttle2rueck);
        e.addShuttle(shuttle3rueck);
        e.addShuttle(shuttle4rueck);

        eventRepository.save(e);

        Set<Halt> halt1hin = new HashSet<>();
        Set<Halt> halt1rueck = new HashSet<>();
        Set<Halt> halt2hin = new HashSet<>();
        Set<Halt> halt2rueck = new HashSet<>();
        Set<Halt> halt3hin = new HashSet<>();
        Set<Halt> halt3rueck = new HashSet<>();
        Set<Halt> halt4hin = new HashSet<>();
        Set<Halt> halt4rueck = new HashSet<>();

        /* @formatter:off */
        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(10, 30), 27.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(11, 30), 22.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(12, 45), 20, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(13, 30), 17.5, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 45), 15, shuttle1hin));
        halt1hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(16, 45), 10, shuttle1hin));
        shuttle1hin.setHaltestellen(halt1hin);
        shuttleRepository.save(shuttle1hin);

        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "69", "27404", "Zeven"), LocalTime.of(18, 45), 27.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhof Bremen Mahndorf", "", "28307", "Hemelingen"), LocalTime.of(17, 45), 22.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Hauptbahnhof Osnabrück", "", "49074", "Osnabrück"), LocalTime.of(16, 30), 20, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Am Bahnhof", "1B", "33602", "Bielefeld"), LocalTime.of(15, 45), 17.5, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Bahnhofstraße", "29", "33102", "Paderborn"), LocalTime.of(14, 30), 15, shuttle1rueck));
        halt1rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(12, 30), 10, shuttle1rueck));
        shuttle1rueck.setHaltestellen(halt1rueck);
        shuttleRepository.save(shuttle1rueck);

        halt2hin.add(new Halt(new PostAdresse("Mühlendamm", "3", "24114", "Kiel"), LocalTime.of(9, 0), 35, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Wasbecker Straße", "330", "24537", "Neumünster"), LocalTime.of(9, 30), 30, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Paul-Ehrlich-Straße", "", "22763", "Hamburg"), LocalTime.of(10, 30), 27.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Rahrsberg", "7", "29614", "Soltau"), LocalTime.of(11, 15), 25, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Busbahnhof Celle", "", "29221", "Celle"), LocalTime.of(12, 0), 22.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("U-Bahn Hannover Zoo", "", "30175", "Hannover"), LocalTime.of(12, 45), 20, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Bahnhof Hildesheim", "", "31134", "Hildesheim"), LocalTime.of(13, 15), 17.5, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Busbahnhof Göttingen", "", "37073", "Göttingen"), LocalTime.of(14, 15), 15, shuttle2hin));
        halt2hin.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(15, 45), 10, shuttle2hin));
        shuttle2hin.setHaltestellen(halt2hin);
        shuttleRepository.save(shuttle2hin);

        halt2rueck.add(new Halt(new PostAdresse("Mühlendamm", "3", "24114", "Kiel"), LocalTime.of(21, 15), 35, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Wasbecker Straße", "330", "24537", "Neumünster"), LocalTime.of(20, 45), 30, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Paul-Ehrlich-Straße", "", "22763", "Hamburg"), LocalTime.of(19, 45), 27.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Rahrsberg", "7", "29614", "Soltau"), LocalTime.of(19, 0), 25, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Busbahnhof Celle", "", "29221", "Celle"), LocalTime.of(18, 15), 22.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("U-Bahn Hannover Zoo", "", "30175", "Hannover"), LocalTime.of(17, 30), 20, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Bahnhof Hildesheim", "", "31134", "Hildesheim"), LocalTime.of(16, 0), 17.5, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Busbahnhof Göttingen", "", "37073", "Göttingen"), LocalTime.of(15, 0), 15, shuttle2rueck));
        halt2rueck.add(new Halt(new PostAdresse("Knallhütter Straße", "43", "34225", "Baunatal"), LocalTime.of(13, 30), 10, shuttle2rueck));
        shuttle2rueck.setHaltestellen(halt2rueck);
        shuttleRepository.save(shuttle2rueck);

        halt3hin.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(9, 30), 32.5d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(12, 0), 27.5d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(13, 0), 25d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(14, 15), 22.5d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(15, 15), 20d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(16, 15), 15d, shuttle3hin));
        halt3hin.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(16, 45), 10d, shuttle3hin));
        shuttle3hin.setHaltestellen(halt3hin);
        shuttleRepository.save(shuttle3hin);

        halt3rueck.add(new Halt(new PostAdresse("Liebigstraße", "12", "35390", "Gießen"), LocalTime.of(12, 30), 10d, shuttle3rueck));
        halt3rueck.add(new Halt(new PostAdresse("Max-Holder-Straße", "2", "60437", "Frankfurt am Main"), LocalTime.of(13, 0), 15d, shuttle3rueck));
        halt3rueck.add(new Halt(new PostAdresse("Robert-Koch-Straße", "1", "64331", "Weiterstadt"), LocalTime.of(14, 0), 20d, shuttle3rueck));
        halt3rueck.add(new Halt(new PostAdresse("S-Bahnhof Pfaffengrund", "", "69123", "Heidelberg"), LocalTime.of(15, 0), 22.5d, shuttle3rueck));
        halt3rueck.add(new Halt(new PostAdresse("A8 Ausfahrt Hohenäckerallee", "", "75177", "Pforzheim"), LocalTime.of(16, 15), 25d, shuttle3rueck));
        halt3rueck.add(
                new Halt(new PostAdresse("Filderbahnstraße/Klinkerstraße", "", "70771", "Echterdingen"), LocalTime.of(17, 15), 27.5d, shuttle3rueck));
        halt3rueck.add(new Halt(new PostAdresse("Bertha-von-Suttner-Weg", "1", "81245", "München"), LocalTime.of(19, 45), 32.5d, shuttle3rueck));
        shuttle3rueck.setHaltestellen(halt3rueck);
        shuttleRepository.save(shuttle3rueck);

        halt4hin.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(7, 45), 32.5d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(9, 30), 30d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(10, 0), 25d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("(TotalEnergies Autothof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(11, 0), 22.5d,
                shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(11, 45), 17.5d,
                shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 0), 15d, shuttle4hin));
        halt4hin.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(15, 15), 12.5d, shuttle4hin));
        shuttle4hin.setHaltestellen(halt4hin);
        shuttleRepository.save(shuttle4hin);

        halt4rueck.add(new Halt(new PostAdresse("Bahnhofsplatz", "", "99734", "Nordhausen"), LocalTime.of(12, 0), 12.5d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Busbahnhof Sangerhausen", "", "06526", "Sangerhausen"), LocalTime.of(14, 30), 15d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Bushaltestelle am Bahnhof Leipzig/Halle Flughafen", "", "04435", "Schkeuditz"), LocalTime.of(17, 30), 17.5d,
                shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("(TotalEnergies Autohof an der A9) Sandbreite", "1", "06869", "Coswig"), LocalTime.of(18, 15), 22.5d,
                shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Vetschauer Straße", "70", "03048", "Cottbus"), LocalTime.of(19, 15), 32.5d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Hildegard-Knef-Platz", "", "10829", "Berlin"), LocalTime.of(19, 45), 30d, shuttle4rueck));
        halt4rueck.add(new Halt(new PostAdresse("Bushaltestelle Hans-Albers-Straße", "", "14480", "Potsdam Südost"), LocalTime.of(21, 30), 25d, shuttle4rueck));
        shuttle4rueck.setHaltestellen(halt4rueck);
        shuttleRepository.save(shuttle4rueck);
    }
}
