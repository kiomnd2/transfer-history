package com.kakaopay.history.service;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.dto.BranchDto;
import com.kakaopay.history.dto.BranchListDto;
import com.kakaopay.history.exception.EmptyResultException;
import com.kakaopay.history.repository.branch.SearchCondition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class InquireService {

    private final HistoryService historyService;

    private final AccountService accountService;

    private final BranchService branchService;

    /**
     * 연도별로 거래 총량이 가장 높은 거래유저 정보를 가져옵니다
     * @param years 조회하고자 하는 연도 리스트
     * @return 거래 금액 정보
     */
    @Transactional(readOnly = true)
    public List<AmountDto> getMostAmountAccount(int... years) {

        List<AmountDto> result = new ArrayList<>();
        // 연도별로 유저별 총 거래정보를 가져옴
        for (int year : years) {
            List<AmountDto> accountList = historyService.getSortedAmountList(year);
            result.add(accountList.get(0)); // 첫번째 리스트 담음
        }
        return result;
    }

    /**
     * 연도별로 거래가 없는 고객을 추출합니다
     * @param years 조회하고자 하는 연도 리스트
     * @return 계좌 정보
     */
    @Transactional(readOnly = true)
    public List<AccountDto> getNoTransUser(int... years) {

        List<AccountDto> result = new ArrayList<>();

        for (int year : years) {
            List<String> accountList = historyService.getAccountList(year);
            List<AccountDto> filteredAccount = accountService.getFilteredAccount(year, accountList);
            result.addAll(filteredAccount);
        }
        return result;
    }


    /**
     * 지점별 총 금액을 높은순으로 정렬합니다.
     * @return 연도별 지점 정보 리스트
     */
    @Transactional(readOnly = true)
    public List<BranchListDto> getBranchAmount() {

        List<BranchListDto> result = new ArrayList<>();

        List<Integer> yearsList = historyService.getYearsList();

        SearchCondition condition = new SearchCondition();

        for (Integer year : yearsList) {
            condition.setYear(year);
            List<BranchDto> branchDtoList = branchService.getSortedBranchList(condition);
            result.add(BranchListDto.builder().year(year).dataList(branchDtoList).build());
        }

        return result;
    }


    /**
     * 지점코드로 총 거래 금액을 조회합니다.
     * @param brCode 지점코드
     * @return 거래금액을 포함한 지점 정보
     */
    @Transactional(readOnly = true)
    public BranchDto getBranch(String brCode) {

        SearchCondition searchCondition = new SearchCondition();
        List<String> brCodeList = searchCondition.getBrCodeList();
        brCodeList.add(brCode);

        // A코드와 B코드는 통폐합 되었으므로
        if ("A".equals(brCode)) {
            brCodeList.add("B");
        }

        List<BranchDto> branchDtoList = branchService.getBranchList(searchCondition);

        // 비어 있으면 없음
        if (branchDtoList.isEmpty()) {
            throw new EmptyResultException();
        }

        return branchDtoList.get(0);
    }
}
