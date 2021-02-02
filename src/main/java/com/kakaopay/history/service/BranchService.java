package com.kakaopay.history.service;

import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.repository.branch.BranchRepository;
import com.kakaopay.history.repository.branch.SearchCondition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BranchService {

    private final BranchRepository branchRepository;


    /**
     * 정렬된 지점 리스트 정보를 추출합니다.
     * @param condition 동적 조회 조건 ( year, brCodeList )
     * @return 지점정보 리스트
     */
    @Transactional(readOnly = true)
    public List<BranchDto> getSortedBranchList(SearchCondition condition) {
        List<BranchDto> branchList = getBranchList(condition);

        branchList.sort(Comparator.comparing(BranchDto::getSumAmt).reversed()); // 큰 순으로 정렬

        return branchList;
    }

    /**
     * 지정 정보를 호출합니다.
     * @param condition 동적 조회 조건 ( year, brCodeList )
     * @return 지점 정보 리스트
     */
    @Transactional(readOnly = true)
    public List<BranchDto> getBranchList(SearchCondition condition) {
        return branchRepository.findBranchByBrCodeOrYear(condition);
    }
}
