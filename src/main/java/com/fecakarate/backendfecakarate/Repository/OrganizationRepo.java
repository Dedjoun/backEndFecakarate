package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization,Long>, JpaSpecificationExecutor<Organization> {

    Optional<Organization> findByEmail(String email);
    Optional<Organization> findByMatricule(String matricule);
}
