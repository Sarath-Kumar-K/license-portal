package com.ksk.portal.api.license;

import com.ksk.portal.dto.license.AssignLicenseRequest;
import com.ksk.portal.dto.license.AvailableLicenseResponse;
import com.ksk.portal.dto.license.LicenseCheckResponse;
import com.ksk.portal.service.license.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/licenses")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    // =========================
    // ASSIGN LICENSE
    // =========================

    @PostMapping("/assign")
    public String assignLicense(
            @RequestBody AssignLicenseRequest request
    ) {

        boolean assigned =
                licenseService.assignLicense(
                        request.getLicenseId(),
                        request.getMetricId(),
                        request.getManagedObjectId()
                );

        return assigned
                ? "License assigned successfully"
                : "No available licenses";
    }

    // =========================
    // CHECK LICENSE
    // =========================

    @GetMapping("/check/{objectId}")
    public LicenseCheckResponse checkLicense(
            @PathVariable UUID objectId
    ) {

        boolean licensed =
                licenseService.isObjectLicensed(objectId);

        return new LicenseCheckResponse(licensed);
    }

    // =========================
    // AVAILABLE LICENSES
    // =========================

    @GetMapping("/available")
    public AvailableLicenseResponse getAvailable(
            @RequestParam UUID metricId,
            @RequestParam UUID licenseId
    ) {

        int available =
                licenseService.getAvailable(
                        metricId,
                        licenseId
                );

        return new AvailableLicenseResponse(available);
    }
}