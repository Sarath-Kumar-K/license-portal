package com.ksk.portal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksk.portal.domain.product.ProductTenant;

public interface ProductTenantRepository extends JpaRepository<ProductTenant, UUID>{
    
}
