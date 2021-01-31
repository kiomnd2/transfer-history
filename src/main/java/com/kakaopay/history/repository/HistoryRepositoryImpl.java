package com.kakaopay.history.repository;

import com.kakaopay.history.domain.History;
import com.kakaopay.history.domain.QAccount;
import com.kakaopay.history.dto.AmountDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.kakaopay.history.domain.QAccount.*;
import static com.kakaopay.history.domain.QHistory.*;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 계좌별로 총 사용금액과 총 수수료를 구합니다
     * @param year 구하고자 하는 해
     * @return AmountDto
     */
    @Override
    public List<AmountDto> findByYearGroupByAccount(int year) {

        return queryFactory
                .select(Projections.constructor(AmountDto.class,
                        Expressions.constant(year),
                        account.acctNm,
                        account.acctNo,
                        history.amt.sum().subtract(history.fee.sum())))
                .from(history)
                .join(history.account(), account)
                .where(history.trDate.year().eq(year), history.isCnl.isFalse()) // 연도가 일치하고 취소된 거래가 아니여야 함
                .groupBy(history.account())
                .fetch();
    }
}
