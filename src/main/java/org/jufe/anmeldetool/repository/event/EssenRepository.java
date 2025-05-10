package org.jufe.anmeldetool.repository.event;

import org.jufe.anmeldetool.entity.event.Essen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EssenRepository extends JpaRepository<Essen, UUID> {
}
