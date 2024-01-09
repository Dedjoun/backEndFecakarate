package com.fecakarate.backendfecakarate.Models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="licence_price")
public class LicencePrice extends Auditable<String> {

    @Id
    @SequenceGenerator(name = "licence_price_sequence", sequenceName = "licence_price_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "licence_price_sequence")
    private Long Id;
    private String type;
    private BigDecimal Price;
}
