package com.ksk.portal.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ksk.portal.domain.account.Account;

public interface AccountRepository extends JpaRepository<Account,UUID> {

     
}
