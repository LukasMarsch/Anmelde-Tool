package org.jufe.anmeldetool.repository.reise;

import org.jufe.anmeldetool.entity.reise.Shuttle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ShuttleRepository extends JpaRepository<Shuttle, UUID> {

    Set<Shuttle> findAllByDatumOrderById(LocalDate datum);

}
