package org.jufe.anmeldetool.repository.reise;

import org.jufe.anmeldetool.entity.reise.Halt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HaltRepository extends JpaRepository<Halt, UUID> {
}
