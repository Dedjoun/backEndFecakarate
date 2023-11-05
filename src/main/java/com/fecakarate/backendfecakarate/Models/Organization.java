package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Enums.UserType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="organisation")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
