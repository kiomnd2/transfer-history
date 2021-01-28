package com.kakaopay.history.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private final T body;

    public static <T> ApiResponse<T> response (T body) {
        return new ApiResponse<>(body);
    }
}
