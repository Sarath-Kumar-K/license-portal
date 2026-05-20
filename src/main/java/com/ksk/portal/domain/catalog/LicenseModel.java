package com.ksk.portal.domain.catalog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "license_model")
@Getter
@Setter
public class LicenseModel {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String modelCode;

    private String description;
}