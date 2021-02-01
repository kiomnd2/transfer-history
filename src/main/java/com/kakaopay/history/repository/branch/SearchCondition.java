package com.kakaopay.history.repository.branch;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SearchCondition {

    @Setter
    private Integer year;

    private final List<String> brCodeList = new ArrayList<>();
}
