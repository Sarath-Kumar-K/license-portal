package com.ksk.portal.domain.catalog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "duration_definition")
@Getter
@Setter
public class DurationDefinition {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String durationCode;

    private Integer months;

    private String description;
}