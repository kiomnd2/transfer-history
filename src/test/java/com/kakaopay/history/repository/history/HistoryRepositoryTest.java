package com.kakaopay.history.repository.history;

import com.kakaopay.history.dto.AmountDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HistoryRepositoryTest {

    @Autowired
    HistoryRepository historyRepository;

    @Test
    @DisplayName("연도의 유저별로 총거래 합계금액을 구합니다")
    public void findSumAmountGroupedAccount() {
        // 2018년의 결고를 가져와봄
        final int year = 2018;
        List<AmountDto> byYearGroupByAccountIn2018 = historyRepository.findByYearGroupByAccount(year);

        // 11111111 , 1499000
        // 11111112 , 999900
        // 11111114 , 5000000
        assertThat(byYearGroupByAccountIn2018)
                .extracting("year")
                .containsOnly(year);
        assertThat(byYearGroupByAccountIn2018)
                .extracting("name")
                .containsExactly("제이", "에이스", "테드");
        assertThat(byYearGroupByAccountIn2018)
                .extracting("acctNo")
                .containsExactly("11111111", "11111112", "11111114");
        assertThat(byYearGroupByAccountIn2018)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(1499000).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(999900).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(5000000).setScale(2, RoundingMode.CEILING));

        List<AmountDto> byYearGroupByAccountIn2019 = historyRepository.findByYearGroupByAccount(2019);

//        20190405,11111112,1,20000000,0,N
//        20190406,11111112,1,10000000,1000,N
//        20190501,11111111,1,100000,500,N

        assertThat(byYearGroupByAccountIn2019)
                .extracting("year")
                .containsOnly(2019);
        assertThat(byYearGroupByAccountIn2019)
                .extracting("name")
                .containsExactly("제이", "에이스");

        assertThat(byYearGroupByAccountIn2019)
                .extracting("acctNo")
                .containsExactly("11111111", "11111112");

        assertThat(byYearGroupByAccountIn2019)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(99500).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(29999000).setScale(2, RoundingMode.CEILING));
    }

    @DisplayName("연도별 거래 계좌리스트를 반환합니다.")
    @Test
    public void findAccountListByYear() {
        final int year = 2018;

        List<String> accountList1 = historyRepository.findAccountListByYear(year);

        assertThat(accountList1)
                .containsExactly("11111111", "11111112", "11111114");

        List<String> accountList2 = historyRepository.findAccountListByYear(2019);

        assertThat(accountList2)
                .containsExactly("11111111", "11111112");
    }
}
