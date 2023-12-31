package com.fecakarate.backendfecakarate.Models;
import com.fecakarate.backendfecakarate.Enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="member")
public class Member extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String lieuNaissance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id")
    private Organization organization;
    private Grade grade;
    private Fonction fonction;
    private String qrcode;
    private String photo;
    private STATUS printStatus;
    private STATUS licenceStatus;
}
