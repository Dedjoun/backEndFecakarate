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
public class Recus extends Auditable<String> {
    @Id
    @SequenceGenerator(name = "recu_sequence", sequenceName = "recu_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "recu_sequence")
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
