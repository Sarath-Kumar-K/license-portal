package com.ksk.portal.domain.license;
import com.ksk.portal.domain.metric.MetricDefinition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "license_entitlement")
@Getter
@Setter
public class LicenseEntitlement {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "license_id")
    private License license;

    @ManyToOne
    @JoinColumn(name = "metric_definition_id")
    private MetricDefinition metricDefinition;

    private Integer quantity;
}