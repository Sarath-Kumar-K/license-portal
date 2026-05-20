package com.ksk.portal.repository;

import com.ksk.portal.domain.license.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LicenseRepository
        extends JpaRepository<License, UUID> {
}