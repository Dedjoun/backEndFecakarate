package com.fecakarate.backendfecakarate.Dtos.organization;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class ClubDto {
    private String nom;
    private String matricule;
    private String ville;
    private String region;
    private String departement;
    private String responsable;
    private String quartier;
    private String qrcode;
    private STATUS printStatus;
    private String logo;
    private STATUS etat;
}
