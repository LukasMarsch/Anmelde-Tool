package org.jufe.anmeldetool.repository.entschuldigung;

import org.jufe.anmeldetool.entity.entschuldigung.Entschuldigung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntschuldigungRepository extends JpaRepository<Entschuldigung, UUID> {
}
