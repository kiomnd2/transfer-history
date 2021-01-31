package com.kakaopay.history.repository;

import com.kakaopay.history.dto.AmountDto;
import org.junit.jupiter.api.BeforeEach;
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
class HistoryRepositoryImplTest {

    @Autowired
    HistoryRepository historyRepository;

    @Test
    @DisplayName("연도의 유저별로 합계금액을 구합니다")
    public void findSumAmountGroupedAccount() {
        // 2018년의 결고를 가져와봄
        List<AmountDto> byYearGroupByAccountIn2018 = historyRepository.findByYearGroupByAccount(2018);

        // 11111111 , 1499000
        // 11111112 , 999900
        // 11111114 , 5000000
        assertThat(byYearGroupByAccountIn2018)
                .extracting("year")
                .containsOnly(2018);
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
}
