package com.kakaopay.history.repository.history;

import com.kakaopay.history.dto.AccountDto;
import com.kakaopay.history.dto.AmountDto;

import java.util.List;

public interface HistoryRepositoryCustom {

    List<AmountDto> findByYearGroupByAccount(int year);

    List<String> findAccountListByYear(int year);
}