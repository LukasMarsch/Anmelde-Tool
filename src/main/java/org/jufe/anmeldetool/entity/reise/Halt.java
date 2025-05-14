package org.jufe.anmeldetool.entity.reise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jufe.address.PostAdresse;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Halt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    private PostAdresse ort;

    private LocalTime zeit;

    private double preis;

    @ManyToOne
    private Shuttle shuttle;

}
