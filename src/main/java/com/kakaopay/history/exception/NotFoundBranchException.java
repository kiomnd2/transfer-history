package com.kakaopay.history.exception;

public class NotFoundBranchException extends RuntimeException{
    public NotFoundBranchException() {
        super("branch not found error");
    }
}
