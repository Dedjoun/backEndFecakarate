package com.fecakarate.backendfecakarate.Exceptions;

public class OrganizationException extends RuntimeException{

    private String code;
    public OrganizationException(String code, String message) {
        super(message);
        this.code = code;

    }
}
