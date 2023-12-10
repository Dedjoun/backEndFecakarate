package com.fecakarate.backendfecakarate.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="recu")
public class Recus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id")
    private Organization organization;
    private Instant dates;
    private String numeroRecu;
    private String dateAffiliation;
    private String datePaiement;
    private String agencePaiement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facture_id")
    private Facture facture;
    private String region;
}
