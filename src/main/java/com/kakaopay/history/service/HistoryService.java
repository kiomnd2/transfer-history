package com.kakaopay.history.service;

import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.repository.history.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryService {

    private final HistoryRepository historyRepository;



    @Transactional(readOnly = true)
    public List<AmountDto> getSortedAmountList(int year) {
        List<AmountDto> accountList = historyRepository.findByYearGroupByAccount(year);
        accountList.sort(Comparator.comparing(AmountDto::getSumAmt).reversed()); // 유저별 오름차순 정렬
        return accountList;
    }

    @Transactional(readOnly = true)
    public List<String> getAccountList(int year) {
        return historyRepository.findAccountListByYear(year);
    }

    public List<Integer> getYearsList() {
        return historyRepository.findYearsList();
    }
}
