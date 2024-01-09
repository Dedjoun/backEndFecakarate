package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="facture")
public class Facture extends Auditable<String> {
    @Id
    @SequenceGenerator(name = "facture_sequence", sequenceName = "facture_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "facture_sequence")
    private String id;
    private String libelle;
    private String dateDemande;
    private Long nombreLicence;
    private Long nombreArbitre;
    private Long nombreCoach;
    private Long nombreAthlete;
    private Long nombreEncadreur;
    private Long nombreMember;
    private Long nombrekinney;
    @OneToOne
    private Organization organization;
    private BigDecimal amount;
    private STATUS status;
}
