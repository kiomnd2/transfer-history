package com.kakaopay.history.repository;

import com.kakaopay.history.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAcctNo(String acctNo);
}
