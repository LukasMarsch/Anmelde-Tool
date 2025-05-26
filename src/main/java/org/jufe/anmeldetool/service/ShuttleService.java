package org.jufe.anmeldetool.service;

import org.jufe.anmeldetool.entity.event.Event;
import org.jufe.anmeldetool.entity.reise.Halt;
import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.jufe.anmeldetool.repository.reise.HaltRepository;
import org.jufe.anmeldetool.repository.reise.ShuttleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShuttleService extends BaseService<Shuttle> {

    private final ShuttleRepository shuttleRepository;

    private final HaltRepository haltRepository;

    @Autowired
    public ShuttleService(ShuttleRepository shuttleRepository, HaltRepository haltRepository) {
        super(shuttleRepository);
        this.shuttleRepository = shuttleRepository;
        this.haltRepository = haltRepository;
    }

    public Shuttle createShuttle(Event e, Set<Halt> haltestellen, String detail) {
        return shuttleRepository.save(new Shuttle(e, haltestellen, detail));
    }

}
