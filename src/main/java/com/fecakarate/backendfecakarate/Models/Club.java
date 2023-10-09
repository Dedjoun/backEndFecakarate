package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.Etat;
import com.fecakarate.backendfecakarate.Enums.PrintStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;
    private String matricule;
    private String ville;
    private String region;
    private String departement;
    private String responsable;
    private String quartier;
    private String qrcode;
    private PrintStatus printStatus;
    private String logo;
    private Etat etat;
}
