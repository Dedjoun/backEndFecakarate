package com.fecakarate.backendfecakarate.Enums;

import lombok.Getter;

@Getter
public enum Region {
    CENTRE ("CENTRE"),
    EST("EST"),
    EXTREMENORD("EXTREME-NORD"),
    LITTORAL("LITTORAL"),
    NORD("NORD"),
    OUEST("OUEST"),
    SUD("SUD"),
    NORDOUEST("NORD-OUEST"),
    SUDOUEST("SUD-OUEST");
    private final String description;

    Region(String description){
        this.description = description;
    }
}
