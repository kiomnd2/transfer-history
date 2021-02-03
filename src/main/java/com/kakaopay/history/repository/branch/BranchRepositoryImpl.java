package com.kakaopay.history.repository.branch;

import com.kakaopay.history.dto.BranchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.kakaopay.history.domain.QAccount.account;
import static com.kakaopay.history.domain.QBranch.branch;
import static com.kakaopay.history.domain.QHistory.history;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BranchRepositoryImpl implements BranchRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 지점코드나 연도를 입력받아 지점을 추출합니다
     * @param condition 연도 or 지점코드
     * @return 지점정보 리스트
     */
    @Override
    public List<BranchDto> findBranchByBrCodeOrYear(SearchCondition condition) {
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
                        branchEq(condition.getBrCodeList()),
                        yearEq(condition.getYear()))
                .groupBy(branch)
                .fetch();
    }


    private BooleanExpression branchEq(List<String> brCodeList) {
        return !brCodeList.isEmpty() ? branch.brCode.in(brCodeList) : null;
    }
    private BooleanExpression yearEq(Integer year) { // Predicate 보다 booleanExpression 사용
        return year != null ? history.trDate.year().eq(year) : null;
    }

}
