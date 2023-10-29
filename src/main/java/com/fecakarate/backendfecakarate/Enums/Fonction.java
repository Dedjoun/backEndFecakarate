package com.fecakarate.backendfecakarate.Enums;

public enum Fonction {

    pr("PRESIDENT"),

    vpr("VICE PRESIDENT"),

    sg("SECRETAIRE GENERAL"),

    cdf("CHEF DU DEPARTEMENT FINANCIER"),

    entreneur("ENTRAINEUR NATIONAL"),
    arbitre("ARBITRE"),
    athletes("ATHLETE"),
    coach("COACH"),
    kinney("KINNEY"),
    member("MEMBRE");

    private String description;

    Fonction(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
