package com.ksk.portal.dto.license;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AssignLicenseRequest {

    private UUID licenseId;

    private UUID metricId;

    private UUID managedObjectId;
}