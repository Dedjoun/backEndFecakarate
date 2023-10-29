package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.GRADE;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Exceptions.MemberException;
import com.fecakarate.backendfecakarate.Models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberServices {

    Member add(MemberDto memberDto) throws MemberException;

    Member update(MemberDto memberDto)throws MemberException;

    Member getById(Long id) throws MemberException;

    String delete(Long id) throws MemberException;

    Page<Member> getALL(Pageable pageable, String matricule,
                        Fonction fonction, STATUS printStatus,
                        GRADE grade,String organisationId,
                        STATUS licenceStatus) throws MemberException;
}
