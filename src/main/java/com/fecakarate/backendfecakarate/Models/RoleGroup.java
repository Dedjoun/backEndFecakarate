package com.fecakarate.backendfecakarate.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="role_group")
public class RoleGroup {@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
