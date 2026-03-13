package com.ksk.portal.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksk.portal.domain.metric.MetricDefinition;


public interface MetricDefinitionRepository extends JpaRepository<MetricDefinition, UUID>{
    Optional<MetricDefinition> findByMetricCode(String metricCode);
}
