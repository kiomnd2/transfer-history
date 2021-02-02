package com.kakaopay.history.service;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.repository.account.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountService {

    private final AccountRepository accountRepository;


    public List<AccountDto> getFilteredAccount(int year, List<String> accountList) {
        return accountRepository.findByAcctNoNotIn(year, accountList);
    }

}
