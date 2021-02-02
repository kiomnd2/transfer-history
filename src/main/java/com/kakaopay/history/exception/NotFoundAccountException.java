package com.kakaopay.history.exception;

public class NotFoundAccountException extends RuntimeException{
    public NotFoundAccountException() {
        super("account not found error");
    }
}
