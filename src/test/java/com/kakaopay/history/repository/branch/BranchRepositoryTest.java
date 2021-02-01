package com.kakaopay.history.repository.branch;

import com.kakaopay.history.dto.BranchDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BranchRepositoryTest {

    @Autowired
    BranchRepository branchRepository;


    @DisplayName("연도를 입력받고, 해당 연도의 지점별 총금액을 반환합니다.")
    @Test
    public void findBranchByYears() {

        final int year = 2018;

        SearchCondition condition = new SearchCondition();
        condition.setYear(year);

        List<BranchDto> branchByYears = branchRepository.findBranchByBrCodeOrYear(condition);
        assertThat(branchByYears)
                .extracting("brName")
                .containsOnly("판교점", "분당점");

        assertThat(branchByYears)
                .extracting("brCode")
                .containsOnly("A", "B");

        assertThat(branchByYears)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(2498900).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(5000000).setScale(2, RoundingMode.CEILING)
                );
    }

    @DisplayName("모든 지점의 정보를 반환합니다.")
    @Test
    public void findBranch() {
        SearchCondition condition = new SearchCondition();

        List<BranchDto> branchByYears = branchRepository.findBranchByBrCodeOrYear(condition);
        assertThat(branchByYears)
                .extracting("brName")
                .containsOnly("판교점", "분당점");

        assertThat(branchByYears)
                .extracting("brCode")
                .containsOnly("A", "B");

        assertThat(branchByYears)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(32597400).setScale(2, RoundingMode.CEILING),
                        BigDecimal.valueOf(5000000).setScale(2, RoundingMode.CEILING)
                );
    }


    @DisplayName("지점을 입력받고, 해당 지점의 총거래금액 정보를 반환합니다.")
    @Test
    public void findBranchByCode() {
        SearchCondition condition = new SearchCondition();

        condition.getBrCodeList().add("A");

        List<BranchDto> branchByYears = branchRepository.findBranchByBrCodeOrYear(condition);
        assertThat(branchByYears)
                .extracting("brName")
                .containsOnly("판교점");

        assertThat(branchByYears)
                .extracting("brCode")
                .containsOnly("A");

        assertThat(branchByYears)
                .extracting("sumAmt")
                .containsExactly(
                        BigDecimal.valueOf(32597400).setScale(2, RoundingMode.CEILING)
                );
    }
}
