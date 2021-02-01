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

@SpringBootTest
class InquireServiceTest {

    @Autowired
    InquireService inquireService;

    @Test
    @DisplayName("연도별로 총합을 정렬하여 가장 높은 금액을 가진 계좌를 검증합니다")
    //getMostAmountAccount
    public void findMostAmountAccountListByYears() {
        final int year1 = 2018;
        final int year2 = 2019;
        List<AmountDto> mostAmountAccount = inquireService.getMostAmountAccount(year1, year2);


        assertThat(mostAmountAccount.size()).isEqualTo(2);

        assertThat(mostAmountAccount)
                .extracting("year")
                .containsExactly(year1, year2);
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

    @DisplayName("연도별로 연도에 거래가 없는 유저 리스트를 반환합니다")
    @Test
    public void findAccountListNotInByYears() {
        final int year1 = 2018;
        final int year2 = 2019;
        List<AccountDto> noTransUser = inquireService.getNoTransUser(year1, year2);

        // 2019 년에 거래 안한고객 1명
        assertThat(noTransUser.size()).isEqualTo(1);


        assertThat(noTransUser)
                .extracting("year")
                .containsExactly(year2);
        assertThat(noTransUser)
                .extracting("acctNo")
                .containsExactly("11111114");


    }

    @DisplayName("연도별 지점의 총 합계금액을 금액순으로 정렬합니다")
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

    @DisplayName("지점을 입력받고 해당지점의 총거래금액을 계산합니다.")
    @Test
    public void getBranchByBrCode() {

        final String brCode = "A";
        
        BranchDto dto = inquireService.getBranch(brCode);

        assertThat(dto.getBrCode()).isEqualTo(brCode);
        assertThat(dto.getBrName()).isEqualTo("판교점");
        assertThat(dto.getSumAmt()).isEqualByComparingTo(
                BigDecimal.valueOf(32597400).setScale(2, RoundingMode.CEILING));


    }
}
