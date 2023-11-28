package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Enums.UserType;
import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="organization")
public class Organization  extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @Enumerated(EnumType.STRING)
    private STATUS printStatus;
    @Enumerated(EnumType.STRING)
    private STATUS licenceStatus;
    private String logo;
    @Enumerated(EnumType.STRING)
    private STATUS etat;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
