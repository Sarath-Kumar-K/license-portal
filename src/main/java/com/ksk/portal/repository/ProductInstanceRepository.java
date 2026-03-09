package com.ksk.portal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksk.portal.domain.product.ProductInstance;

public interface ProductInstanceRepository extends JpaRepository<ProductInstance,UUID>{
    
}
