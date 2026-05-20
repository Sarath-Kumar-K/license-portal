package com.ksk.portal.repository;

import com.ksk.portal.domain.license.LicenseEntitlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LicenseEntitlementRepository
        extends JpaRepository<LicenseEntitlement, UUID> {

    Optional<LicenseEntitlement>
    findByLicense_IdAndMetricDefinition_Id(UUID licenseId, UUID metricDefinitionId);
}