package com.kakaopay.history.repository.branch;

import com.kakaopay.history.dto.BranchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BranchRepositoryTest {

    @Autowired
    BranchRepository branchRepository;


    @Test
    public void findBranchByYears() {

        SearchCondition condition = new SearchCondition();
        condition.setYear(2018);

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


    @Test
    public void findBranchByCode() {
        SearchCondition condition = new SearchCondition();

        condition.setBrCode("A");
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
