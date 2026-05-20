package com.ksk.portal.repository;

import com.ksk.portal.domain.license.LicenseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LicenseAssignmentRepository
        extends JpaRepository<LicenseAssignment, UUID> {

    boolean existsByManagedObjectIdAndStatus(
            UUID objectId,
            String status
    );

    @Query("""
        SELECT COALESCE(SUM(a.quantity), 0)
        FROM LicenseAssignment a
        WHERE a.metric.id = :metricId
        AND a.status = 'ACTIVE'
    """)
    int getTotalAssigned(UUID metricId);
}