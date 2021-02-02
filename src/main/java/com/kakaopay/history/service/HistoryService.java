package com.kakaopay.history.service;

import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.repository.history.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryService {

    private final HistoryRepository historyRepository;

    /**
     * 정렬된 계좌별 거래 총액 정보를 추출합니다
     * @param year 연도
     * @return 계좌 거래 정보
     */
    @Transactional(readOnly = true)
    public List<AmountDto> getSortedAmountList(int year) {
        List<AmountDto> accountList = historyRepository.findByYearGroupByAccount(year);
        accountList.sort(Comparator.comparing(AmountDto::getSumAmt).reversed()); // 유저별 오름차순 정렬
        return accountList;
    }

    /**
     * 연도별 실제 거래 리스트를 추출합니다
     * @param year 연도
     * @return 계좌 리스트
     */
    @Transactional(readOnly = true)
    public List<String> getAccountList(int year) {
        return historyRepository.findAccountListByYear(year);
    }

    /**
     * 연도 리스트를 추출합니다
     * @return 거래 연도 리스트
     */
    public List<Integer> getYearsList() {
        return historyRepository.findYearsList();
    }
}
