package com.kakaopay.history.service;

import com.kakaopay.history.dto.AmountDto;
import com.kakaopay.history.repository.HistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class InquireService {

    private final HistoryRepository historyRepository;

    /**
     * 연도별로 거래 총량이 가장 높은 거래유저 정보를 가져옵니다
     * @param years
     * @return
     */
    @Transactional(readOnly = true)
    public List<AmountDto> getMostAmountAccount(int... years) {

        List<AmountDto> result = new ArrayList<>();
        // 연도별로 유저별 총 거래정보를 가져옴
        for (int year : years) {
            List<AmountDto> accountList = historyRepository.findByYearGroupByAccount(year);
            accountList.sort(Comparator.comparing(AmountDto::getSumAmt).reversed()); // 유저별 오름차순 정렬
            result.add(accountList.get(0)); // 첫번째 리스트 담음
        }

        return result;
    }
}
