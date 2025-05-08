package org.jufe.anmeldetool.entity.entschuldigung;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jufe.address.PostAdresse;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Entschuldigung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private PostAdresse anschrift;
    private String klasse;
}
