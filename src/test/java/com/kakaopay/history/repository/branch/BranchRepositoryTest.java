package com.kakaopay.history.repository.branch;

import com.kakaopay.history.dto.BranchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BranchRepositoryTest {

    @Autowired
    BranchRepository branchRepository;


    @Test
    public void findBranchByYears() {
        List<BranchDto> branchByYears = branchRepository.findBranchByYears(2018);

        for (BranchDto branchByYear : branchByYears) {
            System.out.println("branchByYear.getBrCode() = " + branchByYear.getBrCode());
            System.out.println("branchByYear.getBrName() = " + branchByYear.getBrName());
            System.out.println("branchByYear.getSumAmt() = " + branchByYear.getSumAmt());
        }
    }

}
