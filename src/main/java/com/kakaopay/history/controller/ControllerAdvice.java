package com.kakaopay.history.controller;

import com.kakaopay.history.exception.BranchCodeNotFoundException;
import com.kakaopay.history.exception.EmptyResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BranchCodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<String>> validate(BranchCodeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(e.getMessage()));
    }

    @ExceptionHandler(EmptyResultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> validate(EmptyResultException e) {
        return ResponseEntity.badRequest().body(ApiResponse.fail(e.getMessage()));
    }
}
