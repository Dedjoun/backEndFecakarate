package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepo extends JpaRepository<Organisation,Long>, JpaSpecificationExecutor<Organisation> {
}
