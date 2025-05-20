package org.jufe.anmeldetool.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class BaseService<T> {

    private static final Logger LOGGER = LogManager.getLogger();

    protected final JpaRepository<T, UUID> repo;

    @Transactional
    public <S extends T> void save(S entity) {
        LOGGER.info(() -> String.format("Repository speichert %s: %s", entity.getClass(), entity));
        repo.save(entity);
    }

}