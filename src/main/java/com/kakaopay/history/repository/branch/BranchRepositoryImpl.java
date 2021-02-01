package com.kakaopay.history.repository.branch;

import com.kakaopay.history.domain.Branch;
import com.kakaopay.history.domain.QAccount;
import com.kakaopay.history.domain.QBranch;
import com.kakaopay.history.domain.QHistory;
import com.kakaopay.history.dto.BranchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.kakaopay.history.domain.QAccount.*;
import static com.kakaopay.history.domain.QBranch.*;
import static com.kakaopay.history.domain.QHistory.*;
import static org.springframework.util.StringUtils.*;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BranchRepositoryImpl implements BranchRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<BranchDto> findBranchByBrCode(String brCode) {
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
                .where(history.isCnl.isFalse(),
                        branchEq(brCode)) // 연도가 일치하고 취소된 거래가 아니여야 함
                .groupBy(branch)
                .fetch();
    }


    private BooleanExpression branchEq(String brCode) { // Predicate 보다 booleanExpression 사용
        return hasText(brCode) ? branch.brCode.eq(brCode) : null;
    }

}