package com.ksk.portal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksk.portal.domain.metric.ObjectMetricUsage;

public interface ObjectMetricUsageRepository extends JpaRepository<ObjectMetricUsage,UUID>{
    
}
