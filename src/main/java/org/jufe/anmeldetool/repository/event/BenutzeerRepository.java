package org.jufe.anmeldetool.repository.event;

import org.jufe.anmeldetool.entity.event.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BenutzeerRepository extends JpaRepository<Benutzer, UUID> {
}
