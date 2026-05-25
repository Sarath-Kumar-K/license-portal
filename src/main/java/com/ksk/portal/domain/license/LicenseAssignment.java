package com.ksk.portal.domain.license;

import com.ksk.portal.domain.common.enums.AssignmentStatus;
import com.ksk.portal.domain.managedobject.ManagedObject;
import com.ksk.portal.domain.metric.MetricDefinition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "license_assignment")
@Getter
@Setter
public class LicenseAssignment {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "license_id")
    private License license;

    @ManyToOne
    @JoinColumn(name = "metric_definition_id")
    private MetricDefinition metric;

    @ManyToOne
    @JoinColumn(name = "managed_object_id")
    private ManagedObject managedObject;

    private Integer quantity;

    private Instant validFrom;

    private Instant validTo;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
}