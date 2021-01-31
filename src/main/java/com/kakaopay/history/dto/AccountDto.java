package com.kakaopay.history.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDto {

    private final String acctNo;

    private final String acctNm;
}
