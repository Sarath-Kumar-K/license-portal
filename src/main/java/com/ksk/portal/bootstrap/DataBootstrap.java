package com.ksk.portal.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ksk.portal.domain.account.Account;
import com.ksk.portal.domain.managedobject.ManagedObject;
import com.ksk.portal.domain.product.ProductInstance;
import com.ksk.portal.domain.product.ProductTenant;
import com.ksk.portal.repository.AccountRepository;
import com.ksk.portal.repository.ManagedObjectRepository;
import com.ksk.portal.repository.ProductInstanceRepository;
import com.ksk.portal.repository.ProductTenantRepository;

@Configuration
public class DataBootstrap {

    @Bean
    CommandLineRunner demo(
        AccountRepository accountRepo,
        ProductInstanceRepository productRepo,
        ProductTenantRepository tenantRepo,
        ManagedObjectRepository objectRepo
    ) {
        return args -> {
            Account account = 
                    Account.builder()
                            .id(UUID.randomUUID())
                            .accountType("CUSTOMER")
                            .name("ABC Corp")
                            .build();
                            account.setParentAccount(account);
                            accountRepo.save(account);
            
            ProductInstance pi = productRepo.save(
                    ProductInstance.builder()
                            .id(UUID.randomUUID())
                            .account(account)
                            .productCode("HYPERV_BACKUP")
                            .instanceUid("instance-001")
                            .build()
            );
            ProductTenant tenant = tenantRepo.save(
                    ProductTenant.builder()
                            .id(UUID.randomUUID())
                            .productInstance(pi)
                            .tenantCode("PROD")
                            .name("Production")
                            .build()
            );
            ManagedObject backup = objectRepo.save(
                    ManagedObject.builder()
                            .id(UUID.randomUUID())
                            .objectUid("backup-01")
                            .productTenant(tenant)
                            .objectType("BACKUP")
                            .name("HyperV Backup")
                            .productInstance(pi)
                            .build()
            );
            System.out.println("Vertical slice created successfully.");
        };
    }
}
