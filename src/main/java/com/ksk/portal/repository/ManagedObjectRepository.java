package com.ksk.portal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksk.portal.domain.managedobject.ManagedObject;

public interface ManagedObjectRepository extends JpaRepository<ManagedObject, UUID>{
    
}
