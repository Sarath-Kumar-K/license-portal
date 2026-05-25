package com.ksk.portal.domain.catalog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.ksk.portal.domain.common.enums.LicenseModelType;

@Entity
@Table(name = "license_model")
@Getter
@Setter
public class LicenseModel {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LicenseModelType modelCode;

    private String description;
}