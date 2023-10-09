package com.fecakarate.backendfecakarate.Models;
import com.fecakarate.backendfecakarate.Enums.*;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String lieuNaissance;
    @ManyToOne
    private Club club;
    private Grade grade;
    private String qrcode;
    private Etat etat;
    private String photo;
}
