package com.kakaopay.history.repository.account;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findAccountListNotIn() {

        List<String> accList = new ArrayList<>();
        accList.add("11111112");
        accList.add("11111114");


        List<AccountDto> accountDtoList =
                accountRepository.findByAcctNoNotIn(2018, accList);

        assertThat(accountDtoList)
                .extracting("year")
                .containsOnly(2018);
        assertThat(accountDtoList)
                .extracting("acctNm")
                .containsOnly("제이");
        assertThat(accountDtoList)
                .extracting("acctNo")
                .containsOnly("11111111");

    }
}
