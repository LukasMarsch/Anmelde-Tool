package org.jufe.anmeldetool.entity.reise;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Halt extends BaseEntity implements Serializable {

    private PostAdresse ort;

    private LocalTime zeit;

    private double preis;

    @ManyToOne
    private Shuttle shuttle;

}
