package org.jufe.anmeldetool.entity;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    protected UUID id;

    protected BaseEntity() {}

}
