package com.kakaopay.history.repository;

import com.kakaopay.history.domain.History;
import com.kakaopay.history.dto.AmountDto;

import java.util.List;

public interface HistoryRepositoryCustom {
    List<AmountDto> findByYearGroupByAccount(int year);
}
