package com.ksk.portal.domain.account;

import java.time.Instant;
import java.util.UUID;

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

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "parent_account_id", nullable = false)
    private Account parentAccount;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private Instant createdAt = Instant.now();

}
