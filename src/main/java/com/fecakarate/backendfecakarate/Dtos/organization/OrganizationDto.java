package com.fecakarate.backendfecakarate.Dtos.organization;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OrganizationDto {
    private String nom;
    private String responsable;
    private String region;
    private String departement;
    private String arrondissement;
    private String ville;
    private String quartier;
    private String email;
    private String contact;
}
