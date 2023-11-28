package com.fecakarate.backendfecakarate.Dtos.organization;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OrganizationDto {
    private String id;
    private String nom;
    private String matricule;
    private String ville;
    private String region;
    private String departement;
    private String responsable;
    private String quartier;
    private String arrondissement;
    private String email;
    private String contact;
    private String qrcode;
    private STATUS printStatus;
    private STATUS licenceStatus;
    private String logo;
    private STATUS etat;
    private UserType userType;
}
