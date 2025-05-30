package org.jufe.anmeldetool.entity.entschuldigung;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jufe.address.PostAdresse;
import org.jufe.anmeldetool.entity.BaseEntity;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Entschuldigung extends BaseEntity implements Serializable {

    private String name;

    private PostAdresse anschrift;

    private String klasse;

}
