package com.kakaopay.history.service;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.dto.BranchListDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InquireServiceTest {

    @Autowired
    InquireService inquireService;

    @Test
    @DisplayName("연도를 입력받고, 각 연도별로 총합을 정렬하여 가장 높은 금액을 가진 계좌를 검증합니다")
    //getMostAmountAccount
    public void findMostAmountAccountListByYears() {
        List<AmountDto> mostAmountAccount = inquireService.getMostAmountAccount(2018, 2019);


        assertThat(mostAmountAccount.size()).isEqualTo(2);

        assertThat(mostAmountAccount)
                .extracting("year")
                .containsExactly(2018, 2019);
        assertThat(mostAmountAccount)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(5000000).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(29999000).setScale(2, RoundingMode.CEILING));
        assertThat(mostAmountAccount)
                .extracting("acctNo")
                .containsExactly("11111114", "11111112");

        assertThat(mostAmountAccount)
                .extracting("name")
                .containsExactly("테드", "에이스");
    }

    @Test
    public void findAccountListNotInByYears() {
        List<AccountDto> noTransUser = inquireService.getNoTransUser(2018, 2019);

        // 2019 년에 거래 안한고객 1명
        assertThat(noTransUser.size()).isEqualTo(1);


        assertThat(noTransUser)
                .extracting("year")
                .containsExactly(2019);
        assertThat(noTransUser)
                .extracting("acctNo")
                .containsExactly("11111114");


    }

    @Test
    public void findAmountGroupedByBranch() {
        List<BranchListDto> branchAmount = inquireService.getBranchAmount();

        assertThat(branchAmount)
                .extracting("year")
                .containsExactly(2018, 2019);

        BranchListDto branchListDto = branchAmount.get(0);

        List<BranchDto> dataList = branchListDto.getDataList();

        // 2018
        assertThat(dataList)
                .extracting("brName")
                .containsExactly("분당점", "판교점");


        assertThat(dataList)
                .extracting("brCode")
                .containsExactly("B", "A");


        assertThat(dataList)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(5000000).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(2498900).setScale(2, RoundingMode.CEILING)
                );
    }

    @Test
    public void getBranchByBrCode() {

        BranchDto dto = inquireService.getBranch("B");

    }
}
