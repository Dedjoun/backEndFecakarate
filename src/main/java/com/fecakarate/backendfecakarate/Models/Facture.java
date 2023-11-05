package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String libelle;
    private Long number;
    private String datepaie;
    @OneToOne
    private Organization organization;
    @NotNull
    private BigDecimal amount;
    private Instant dateadd;
    private STATUS status;
}
