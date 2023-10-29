package com.fecakarate.backendfecakarate.Dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class OrganisationDto {
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
