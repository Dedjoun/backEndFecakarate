package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepo  extends JpaRepository<Member,Long>, JpaSpecificationExecutor<Member> {

    Member findByMatricule(String matricule);
    List<Member> findAllByFonction(Fonction fonction);
}
