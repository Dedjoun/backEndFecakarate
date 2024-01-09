package com.fecakarate.backendfecakarate.Exceptions;

public class LicencePriceException extends RuntimeException{
    private String code;
    public LicencePriceException(String code, String message) {
        super(message);
        this.code = code;

    }
}
