package com.fecakarate.backendfecakarate.Dtos;

import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.GRADE;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Models.Organisation;
import jakarta.persistence.ManyToOne;
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
    private String organisationId;
    private GRADE grade;
    private Fonction fonction;
    private String qrcode;
    private String photo;
    private STATUS printStatus;
    private STATUS licenceStatus;
}
