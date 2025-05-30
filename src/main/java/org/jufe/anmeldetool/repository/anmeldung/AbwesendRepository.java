package org.jufe.anmeldetool.repository.anmeldung;

import org.jufe.anmeldetool.entity.anmeldung.Abwesend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AbwesendRepository extends JpaRepository<Abwesend, UUID> {

}
