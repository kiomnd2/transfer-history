package com.kakaopay.history.repository.account;

import com.kakaopay.history.dto.AccountDto;

import java.util.List;

public interface AccountRepositoryCustom {

    List<AccountDto> findByAcctNoNotIn(int year, List<String> acctNo);
}
