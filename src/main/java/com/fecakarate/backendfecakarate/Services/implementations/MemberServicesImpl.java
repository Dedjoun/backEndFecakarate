package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.GRADE;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Exceptions.MemberException;
import com.fecakarate.backendfecakarate.Models.Member;
import com.fecakarate.backendfecakarate.Services.interfaces.MemberServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServicesImpl implements MemberServices {
    @Override
    public Member add(MemberDto memberDto) throws MemberException {
        return null;
    }

    @Override
    public Member update(MemberDto memberDto) throws MemberException {
        return null;
    }

    @Override
    public Member getById(Long id) throws MemberException {
        return null;
    }

    @Override
    public String delete(Long id) throws MemberException {
        return null;
    }

    @Override
    public Page<Member> getALL(Pageable pageable, String matricule, Fonction fonction, STATUS printStatus, GRADE grade, String organisationId, STATUS licenceStatus) throws MemberException {
        return null;
    }
}
