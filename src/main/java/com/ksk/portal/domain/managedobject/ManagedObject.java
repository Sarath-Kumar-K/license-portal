package com.ksk.portal.domain.managedobject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

import com.ksk.portal.domain.product.ProductInstance;
import com.ksk.portal.domain.product.ProductTenant;


@Entity
@Table(name="managed_object")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagedObject {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String objectUid;


    @ManyToOne
    @JoinColumn(name = "parent_object_id")
    private ManagedObject parent;

    @ManyToOne
    @JoinColumn(name = "product_instance_id", nullable = false)
    private ProductInstance productInstance;

    @ManyToOne
    @JoinColumn(name = "product_tenant_id")
    private ProductTenant productTenant;

    @Column(nullable = false)
    private String objectType;

    private String name;

    @Builder.Default
    @Column(nullable = false)
    private String state = "Active";

    @Builder.Default
    @Column(nullable = false)
    private Instant createdAt = Instant.now();
    
}
