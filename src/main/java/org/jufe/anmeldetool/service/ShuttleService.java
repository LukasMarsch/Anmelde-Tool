package org.jufe.anmeldetool.service;

import lombok.RequiredArgsConstructor;
import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.repository.reise.ShuttleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShuttleService {

    private final ShuttleRepository shuttleRepository;

    public Shuttle createEmptyShuttle(Event e) {
        return shuttleRepository.save(new Shuttle(e, new HashSet<>(), new HashSet<>(), ""));
    }

    public List<Halt> getAllHinHaltestellen() {
        List<Shuttle> shuttleList = shuttleRepository.findAll();
        List<Halt> alleHaltestellen = new ArrayList<>();
        for (Shuttle shuttle : shuttleList) {
            alleHaltestellen.addAll(shuttle.getHinHaltestellen());
        }
        return alleHaltestellen;
    }

    public List<Halt> getAllRueckHaltestellen() {
        List<Shuttle> shuttleList = shuttleRepository.findAll();
        List<Halt> alleHaltestellen = new ArrayList<>();
        for (Shuttle shuttle : shuttleList) {
            alleHaltestellen.addAll(shuttle.getRueckHaltestellen());
        }
        return alleHaltestellen;
    }

    public void save(Shuttle shuttle) {
        shuttleRepository.save(shuttle);
    }

}
