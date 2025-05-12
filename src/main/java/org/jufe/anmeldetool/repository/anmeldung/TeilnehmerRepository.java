package org.jufe.anmeldetool.repository.anmeldung;

import org.jufe.anmeldetool.entity.anmeldung.Teilnehmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeilnehmerRepository extends JpaRepository<Teilnehmer, UUID> {}
