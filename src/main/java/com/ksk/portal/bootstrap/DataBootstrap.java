package com.ksk.portal.bootstrap;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ksk.portal.domain.account.Account;
import com.ksk.portal.domain.managedobject.ManagedObject;
import com.ksk.portal.domain.metric.MetricDefinition;
import com.ksk.portal.domain.metric.ObjectMetricUsage;
import com.ksk.portal.domain.product.ProductInstance;
import com.ksk.portal.domain.product.ProductTenant;
import com.ksk.portal.repository.AccountRepository;
import com.ksk.portal.repository.ManagedObjectRepository;
import com.ksk.portal.repository.MetricDefinitionRepository;
import com.ksk.portal.repository.ObjectMetricUsageRepository;
import com.ksk.portal.repository.ProductInstanceRepository;
import com.ksk.portal.repository.ProductTenantRepository;

@Configuration
public class DataBootstrap {

    @Bean
    CommandLineRunner demo(
        AccountRepository accountRepo,
        ProductInstanceRepository productRepo,
        ProductTenantRepository tenantRepo,
        ManagedObjectRepository objectRepo,
        MetricDefinitionRepository metricRepo,
        ObjectMetricUsageRepository usageRepo
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
            ManagedObject host = objectRepo.save(
                ManagedObject.builder()
                    .id(UUID.randomUUID())
                    .objectUid("host-01")
                    .productTenant(tenant)
                    .objectType("HOST")
                    .name("HyperV Host")
                    .productInstance(pi)
                    .parent(backup)
                    .build()
            );

            MetricDefinition cpuSocket = metricRepo.save(
                MetricDefinition.builder()
                .id(UUID.randomUUID())
                .metricCode("CPU_SOCKET")
                .unit("COUNT")
                .description("CPU sockets on host")
                .build()
            );

            MetricDefinition vmMetric = metricRepo.save(
                MetricDefinition.builder()
                .id(UUID.randomUUID())
                .metricCode("VM")
                .unit("COUNT")
                .description("Virtual machine count")
                .build()
            );

            ObjectMetricUsage usage = usageRepo.save(
                ObjectMetricUsage.builder().id(UUID.randomUUID()).managedObject(host).metricDefinition(cpuSocket).quantity(2).build()
            );
            System.out.println("Vertical slice created successfully.");
        };
    }
}
