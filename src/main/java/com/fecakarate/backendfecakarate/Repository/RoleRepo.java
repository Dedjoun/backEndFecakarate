package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
