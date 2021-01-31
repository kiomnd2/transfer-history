package com.kakaopay.history.repository.account;

import com.kakaopay.history.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> ,AccountRepositoryCustom{
    Optional<Account> findByAcctNo(String acctNo);
}
