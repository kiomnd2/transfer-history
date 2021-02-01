package com.kakaopay.history.repository.account;

import com.kakaopay.history.dto.AccountDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @DisplayName("거래가 없는 계좌를 반환합니다.")
    @Test
    public void findAccountListNotIn() {

        List<String> accList = new ArrayList<>();
        accList.add("11111112");
        accList.add("11111114");


        final int year = 2018;
        List<AccountDto> accountDtoList =
                accountRepository.findByAcctNoNotIn(year, accList);

        assertThat(accountDtoList)
                .extracting("year")
                .containsOnly(year);
        assertThat(accountDtoList)
                .extracting("acctNm")
                .containsOnly("제이");
        assertThat(accountDtoList)
                .extracting("acctNo")
                .containsOnly("11111111");

    }
}
