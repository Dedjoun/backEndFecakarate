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
    @SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "member_sequence")
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
    private String region;
    private String departement;
    private String contact;
    private String sexe;
}
