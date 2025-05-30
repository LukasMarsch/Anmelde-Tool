package org.jufe.anmeldetool.repository.anmeldung;

import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnmeldungRepository extends JpaRepository<Anmeldung, UUID> {
}
