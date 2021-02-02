package com.kakaopay.history.service;

import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.repository.branch.BranchRepository;
import com.kakaopay.history.repository.branch.SearchCondition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BranchService {

    private final BranchRepository branchRepository;


    public List<BranchDto> getSortedBranchList(SearchCondition condition) {
        List<BranchDto> branchList = getBranchList(condition);

        branchList.sort(Comparator.comparing(BranchDto::getSumAmt).reversed()); // 큰 순으로 정렬

        return branchList;
    }

    public List<BranchDto> getBranchList(SearchCondition condition) {
        return branchRepository.findBranchByBrCodeOrYear(condition);
    }
}
