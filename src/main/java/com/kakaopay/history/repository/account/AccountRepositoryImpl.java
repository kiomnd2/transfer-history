package com.kakaopay.history.repository.account;

import com.kakaopay.history.dto.AccountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.kakaopay.history.domain.QAccount.account;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 입력받은 계좌 번호에 포함되지않는 계좌를 찾습니다
     * @param year 연도
     * @param acctNo 계좌리스트
     * @return 계좌 정보
     */
    @Override
    public List<AccountDto> findByAcctNoNotIn(int year, List<String> acctNo) {
        return queryFactory
                .select(Projections.constructor(
                        AccountDto.class,
                        Expressions.constant(year),
                        account.acctNo,
                        account.acctNm))
                .from(account)
                .where(account.acctNo.notIn(acctNo))
                .fetch();
    }
}
