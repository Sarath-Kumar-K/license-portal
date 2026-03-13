package com.ksk.portal.domain.metric;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metric_definition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricDefinition {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String metricCode;

    @Column(nullable = false)
    private String unit;

    private String description;
    
}
