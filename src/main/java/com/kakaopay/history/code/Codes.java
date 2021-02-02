package com.kakaopay.history.code;

public enum Codes {
    S0000("000", "정상"),
    E4040("404", "잘못된 요청");

    public final String code;

    public final String desc;

    Codes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
