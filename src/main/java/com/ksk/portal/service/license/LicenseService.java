package com.ksk.portal.service.license;

import com.ksk.portal.domain.common.enums.AssignmentStatus;
import com.ksk.portal.domain.license.LicenseAssignment;
import com.ksk.portal.domain.license.LicenseEntitlement;
import com.ksk.portal.domain.managedobject.ManagedObject;
import com.ksk.portal.domain.metric.MetricDefinition;
import com.ksk.portal.exception.LicenseUnavailableException;
import com.ksk.portal.exception.ResourceNotFoundException;
import com.ksk.portal.repository.LicenseAssignmentRepository;
import com.ksk.portal.repository.LicenseEntitlementRepository;
import com.ksk.portal.repository.ManagedObjectRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseEntitlementRepository entitlementRepo;

    private final LicenseAssignmentRepository assignmentRepo;

    private final ManagedObjectRepository managedObjectRepo;

    public boolean isObjectLicensed(UUID objectId) {

        return assignmentRepo.existsByManagedObjectIdAndStatus(
                objectId,
                AssignmentStatus.ACTIVE
        );
    }

    public int getAvailable(UUID metricId, UUID licenseId) {

        LicenseEntitlement entitlement =
                entitlementRepo
                        .findByLicense_IdAndMetricDefinition_Id(
                                licenseId,
                                metricId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "License entitlement not found"
                                )
                        );

        int assigned =
                assignmentRepo.getTotalAssigned(metricId);

        return entitlement.getQuantity() - assigned;
    }

    @Transactional
    public boolean assignLicense(
            UUID licenseId,
            UUID metricId,
            UUID objectId
    ) {

        ManagedObject object =
                managedObjectRepo.findById(objectId)
                        .orElseThrow();

        int available =
                getAvailable(metricId, licenseId);

        if (available <= 0) {
                throw new LicenseUnavailableException(
                        "No available licenses"
                );
        }

        LicenseAssignment assignment =
                new LicenseAssignment();

        assignment.setId(UUID.randomUUID());

        assignment.setManagedObject(object);

        MetricDefinition metric =
                new MetricDefinition();

        metric.setId(metricId);

        assignment.setMetric(metric);

        assignment.setQuantity(1);

        assignment.setValidFrom(Instant.now());

        assignment.setStatus(AssignmentStatus.ACTIVE);

        assignmentRepo.save(assignment);

        return true;
    }
}