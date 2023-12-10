package com.fecakarate.backendfecakarate.Enums;

import lombok.Getter;

@Getter
public enum Departement {
    DJEREM  ("DJEREM "),
    FAROETDEO ("FARO-ET-DEO"),
    MAYOBANYO ("MAYO-BANYO"),
    MBERE  ("MBERE"),
    VINA ("VINA"),
    HAUTESANAGA ("HAUTE-SANAGA"),
    LEKIE ("LEKIE"),
    MBAMETINOUBOU ("MBAM-ET-INOUBOU"),
    MBAMETKIM ("MBAM-ET-KIM"),
    MEFOUETAFAMBA ("MEFOU-ET-AFAMBA"),
    MEFOUETAKONO ("MEFOU-ET-AKONO"),
    MFOUNDI ("MFOUNDI"),
    NYONGETKELLE ("NYONG-ET-KELLE"),
    NYONGETMFOUMOU ("NYONG-ET-MFOUMOU"),
    NYONGETSOO ("NYONG-ET-SO'O"),
    BOUMBAETNGOKO ("BOUMBA-ET-NGOKO"),
    HAUTNYONG ("HAUT-NYONG"),
    KADEY ("KADEY"),
    LOMETDJEREM ("LOM-ET-DJEREM"),
    DIAMARE ("DIAMARE"),
    LOGONEETCHARI ("LOGONE-ET-CHARI"),
    MAYODANAY ("MAYO-DANAY"),
    MAYOKANI ("MAYO-KANI"),
    MAYOSAVA ("MAYO-SAVA"),
    MAYOTSANAGA ("MAYO-TSANAGA"),
    MOUNGO ("MOUNGO"),
    NKAM ("NKAM"),
    SANAGAMARITIME ("SANAGA-MARITIME"),
    WOURI ("WOURI"),
    BENOUE ("BÉNOUÉ"),
    FARO ("FARO"),
    MAYOLOUTI ("MAYO-LOUTI"),
    MAYOREY ("MAYO-REY"),
    BOYO ("BOYO"),
    BUI ("BUI"),
    DONGAMANTUNG ("DONGA-MANTUNG"),
    MENCHUM ("MENCHUM"),
    MEZAM ("MEZAM"),
    MOMO ("MOMO"),
    NGOKETUNJIA ("NGO-KETUNJIA"),
    BAMBOUTOS ("BAMBOUTOS"),
    HAUTNKAM ("HAUT-NKAM"),
    HAUTSPLATEAUX ("HAUTS-PLATEAUX"),
    KOUNGKHI ("KOUNG-KHI"),
    MENOUA ("MENOUA"),
    MIFI ("MIFI"),
    NDE ("NDE"),
    NOUN ("NOUN"),
    DJAETLOBO ("DJA-ET-LOBO"),
    MVILA ("MVILA"),
    OCEAN ("OCEAN"),
    VALLEEDUNTEM ("VALLEE-DU-NTEM"),
    FAKO ("FAKO"),
    KOUPEMANENGOUBA ("KOUPE-MANENGOUBA"),
    LEBIALEM ("LEBIALEM"),
    MANYU ("MANYU"),
    MEME ("MEME"),
    NDIAN ("NDIAN"),
    SUDOUEST("SUD-OUEST");
    private final String description;

    Departement(String description){
        this.description = description;
    }
}
