package com.kakaopay.history.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class AccountDto {

    private final int year;

    private final String acctNo;

    private final String acctNm;

    @Builder
    public AccountDto(int year, String acctNo, String acctNm) {
        this.year = year;
        this.acctNo = acctNo;
        this.acctNm = acctNm;
    }
}
