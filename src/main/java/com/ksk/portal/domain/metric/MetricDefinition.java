package com.ksk.portal.domain.metric;

import java.util.UUID;

import com.ksk.portal.domain.common.enums.MetricCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private MetricCode metricCode;

    @Column(nullable = false)
    private String unit;

    private String description;
    
}
