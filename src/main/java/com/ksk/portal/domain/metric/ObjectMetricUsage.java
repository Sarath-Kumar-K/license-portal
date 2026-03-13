package com.ksk.portal.domain.metric;

import java.time.Instant;
import java.util.UUID;

import com.ksk.portal.domain.managedobject.ManagedObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "object_metric_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectMetricUsage {
    
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "managed_object_id", nullable = false)
    private ManagedObject managedObject;

    @ManyToOne
    @JoinColumn(name = "metric_definition_id", nullable = false)
    private MetricDefinition metricDefinition;

    @Column(nullable = false)
    private Integer quantity;

    @Builder.Default
    @Column(nullable = false)
    private Instant reportedAt = Instant.now();
}
