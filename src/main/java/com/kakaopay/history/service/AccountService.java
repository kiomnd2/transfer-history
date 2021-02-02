package com.kakaopay.history.service;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.repository.account.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * 연도를 받고 계좌정보에서 사용하지 않는 계좌 정보 리스트를 추출합니다
     * @param year 연도
     * @param accountList 총 거래 리스트
     * @return
     */
    @Transactional(readOnly = true)
    public List<AccountDto> getFilteredAccount(int year, List<String> accountList) {
        return accountRepository.findByAcctNoNotIn(year, accountList);
    }

}
