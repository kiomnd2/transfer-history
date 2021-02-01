package com.kakaopay.history.exception;

public class EmptyResultException extends RuntimeException{
    public EmptyResultException() {
        super("result is Empty");
    }
}
