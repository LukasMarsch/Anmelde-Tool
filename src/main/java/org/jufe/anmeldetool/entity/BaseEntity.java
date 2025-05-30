package org.jufe.anmeldetool.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/**
 * Klasse stellt
 */
@Getter
@MappedSuperclass
public class BaseEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    protected UUID id;

    protected BaseEntity() {}

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    protected void setId(UUID uuid) {
        this.id = uuid;
    }

}
