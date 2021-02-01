package com.kakaopay.history.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class BranchDto {

    private final String brName;

    private final String brCode;

    private final BigDecimal sumAmt;

    @Builder
    public BranchDto(String brName, String brCode, BigDecimal sumAmt) {
        this.brName = brName;
        this.brCode = brCode;
        this.sumAmt = sumAmt;
    }
}
