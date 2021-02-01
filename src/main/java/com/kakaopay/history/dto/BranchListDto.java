package com.kakaopay.history.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class BranchListDto {

    private final int year;

    private final List<BranchDto> dataList;

    @Builder
    public BranchListDto(int year, List<BranchDto> dataList) {
        this.year = year;
        this.dataList = dataList;
    }
}
