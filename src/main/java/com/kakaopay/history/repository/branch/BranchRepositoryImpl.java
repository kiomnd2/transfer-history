package com.kakaopay.history.repository.branch;

import com.kakaopay.history.domain.Branch;
import com.kakaopay.history.domain.QAccount;
import com.kakaopay.history.domain.QBranch;
import com.kakaopay.history.domain.QHistory;
import com.kakaopay.history.dto.BranchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static com.kakaopay.history.domain.QAccount.*;
import static com.kakaopay.history.domain.QBranch.*;
import static com.kakaopay.history.domain.QHistory.*;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BranchRepositoryImpl implements BranchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BranchDto> findBranchByYears(int year) {
        return queryFactory
                .select(Projections.constructor(
                        BranchDto.class,
                        branch.brName,
                        branch.brCode,
                        history.amt.sum().subtract(history.fee.sum()).as("sumAmt")
                ))
                .from(history)
                .join(history.account(), account)
                .join(account.branch(), branch)
                .where(history.trDate.year().eq(year), history.isCnl.isFalse()) // 연도가 일치하고 취소된 거래가 아니여야 함
                .groupBy(branch)
                .fetch();
    }
}
