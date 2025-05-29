package org.jufe.anmeldetool.repository.event;

import org.jufe.anmeldetool.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Event findFirstByOrderByVonDesc();

}
