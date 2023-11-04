package com.fecakarate.backendfecakarate.Dtos;

import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class MemberDto {
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String lieuNaissance;
    private Long organisationId;
    private Grade grade;
    private Fonction fonction;
    private String qrcode;
    private String photo;
    private STATUS printStatus;
    private STATUS licenceStatus;
}
