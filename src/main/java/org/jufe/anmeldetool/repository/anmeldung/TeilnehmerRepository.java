package org.jufe.anmeldetool.repository.anmeldung;

import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

@Repository
public interface TeilnehmerRepository extends JpaRepository<Teilnehmer, UUID> {
    Teilnehmer getById(UUID id);

    @Query("SELECT t FROM teilnehmer t WHERE LOWER(t.name) = LOWER(:name)");
    Teilnehmer retrieveByName(@Param("name") String name);
}
