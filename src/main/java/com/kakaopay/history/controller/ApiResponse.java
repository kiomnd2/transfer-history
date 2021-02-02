package com.kakaopay.history.controller;

import com.kakaopay.history.code.Codes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private final String code;

    private final T body;

    public static <T> ApiResponse<T> success (T body) {
        return new ApiResponse<>(Codes.S0000.code, body);
    }

    public static <T> ApiResponse<T> fail (T body) {
        return new ApiResponse<>(Codes.E4040.code, body);
    }
}
