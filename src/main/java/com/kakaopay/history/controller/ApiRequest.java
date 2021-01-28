package com.kakaopay.history.controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiRequest {

    /**
     * 관리점 명
     */
    private final String brName;
}
