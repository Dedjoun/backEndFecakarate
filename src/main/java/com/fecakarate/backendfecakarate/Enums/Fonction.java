package com.fecakarate.backendfecakarate.Enums;

import lombok.Getter;

@Getter
public enum Fonction {

    pr("PRESIDENT"),
    vpr("VICE PRESIDENT"),
    sg("SECRETAIRE GENERAL"),
    cdf("CHEF DU DEPARTEMENT FINANCIER"),
    entreneur("ENTRAINEUR NATIONAL"),
    arbitre("ARBITRE"),
    athletes("ATHLETE"),
    coach("COACH"),
    encadreur("ENCADREUR TECHNIQUE"),
    kinney("PERSONNEL MEDICAL"),
    member("MEMBRE");

    private final String description;

    Fonction(String description){
        this.description = description;
    }


}
