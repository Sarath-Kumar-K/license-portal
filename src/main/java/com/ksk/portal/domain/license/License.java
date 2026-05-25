package com.ksk.portal.domain.license;
import com.ksk.portal.domain.account.Account;
import com.ksk.portal.domain.catalog.LicensePlan;
import com.ksk.portal.domain.common.enums.LicenseStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "license")
@Getter
@Setter
public class License {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "license_plan_id", nullable = false)
    private LicensePlan licensePlanId;

    private Instant startTime;

    private Instant duration;

    @Enumerated(EnumType.STRING)
    private LicenseStatus status;

    private Instant createdAt = Instant.now();
}
