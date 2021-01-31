package com.kakaopay.history.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class AmountDto {

    private final int year;

    private final String name;

    private final String acctNo;

    private final BigDecimal sumAmt;

    @Builder
    public AmountDto(int year, String name, String acctNo, BigDecimal sumAmt) {
        this.year = year;
        this.name = name;
        this.acctNo = acctNo;
        this.sumAmt = sumAmt;
    }
}
