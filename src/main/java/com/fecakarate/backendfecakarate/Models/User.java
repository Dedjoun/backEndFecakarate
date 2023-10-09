package com.fecakarate.backendfecakarate.Models;

import com.fecakarate.backendfecakarate.Enums.ConnexionStatus;
import com.fecakarate.backendfecakarate.Enums.UserType;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String matricule;
    private String email;
    private String password;
    private UserType userType;
    private ConnexionStatus connexionStatus;
}
