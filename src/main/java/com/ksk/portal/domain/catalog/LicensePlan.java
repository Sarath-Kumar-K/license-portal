package com.ksk.portal.domain.catalog;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "license_plan")
@Getter
@Setter
public class LicensePlan {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_metric_id", nullable = false)
    private ProductMetric productMetric;

    @ManyToOne
    @JoinColumn(name = "license_model_id", nullable = false)
    private LicenseModel licenseModel;

    @ManyToOne
    @JoinColumn(name = "duration_definition_id")
    private DurationDefinition durationDefinition;

    @Column(nullable = false, unique = true)
    private String planCode;

    private String name;

    private Boolean active = true;
}