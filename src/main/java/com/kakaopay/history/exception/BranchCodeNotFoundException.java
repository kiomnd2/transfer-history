package com.kakaopay.history.exception;

public class BranchCodeNotFoundException extends RuntimeException {
    public BranchCodeNotFoundException() {
        super("br code not found error");
    }
}
