package com.ksk.portal.domain.license;
import com.ksk.portal.domain.account.Account;
import com.ksk.portal.domain.catalog.LicensePlan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
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

    private String status;

    private Instant createdAt = Instant.now();
}
