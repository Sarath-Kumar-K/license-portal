package com.ksk.portal.dto.license;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AssignLicenseRequest {

    @NotNull
    private UUID licenseId;

    @NotNull
    private UUID metricId;

    @NotNull
    private UUID managedObjectId;
}