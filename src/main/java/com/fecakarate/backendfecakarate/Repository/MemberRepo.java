package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepo  extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member> {

    Member findByMatricule(String matricule);
}
