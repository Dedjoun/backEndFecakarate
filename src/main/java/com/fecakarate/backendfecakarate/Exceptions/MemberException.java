package com.fecakarate.backendfecakarate.Exceptions;

public class MemberException extends RuntimeException{
    private String code;
    public MemberException(String code, String message) {
        super(message);
        this.code = code;

    }
}
