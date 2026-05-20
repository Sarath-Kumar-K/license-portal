package com.ksk.portal.domain.catalog;

import com.ksk.portal.domain.metric.MetricDefinition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "product_metric")
@Getter
@Setter
public class ProductMetric {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "metric_definition_id", nullable = false)
    private MetricDefinition metricDefinition;

    private Boolean active = true;
}