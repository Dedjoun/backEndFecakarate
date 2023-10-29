package com.fecakarate.backendfecakarate.Exceptions;

public class ClubException extends RuntimeException{

    private String code;
    public ClubException(String code, String message) {
        super(message);
        this.code = code;

    }
}
