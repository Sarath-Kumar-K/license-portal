package com.ksk.portal.domain.product;

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

import com.ksk.portal.domain.account.Account;


@Entity
@Table(name="product_instance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInstance {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false, unique = true)
    private String instanceUid;

    @Builder.Default
    @Column(nullable = false)
    private Instant createdAt = Instant.now();
    
}
