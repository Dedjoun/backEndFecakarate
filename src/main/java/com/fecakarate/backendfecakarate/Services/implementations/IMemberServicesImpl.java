package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Exceptions.MemberException;
import com.fecakarate.backendfecakarate.Models.Member;
import com.fecakarate.backendfecakarate.Repository.MemberRepo;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IMemberServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class IMemberServicesImpl implements IMemberServices {

    @Autowired
    private MemberRepo memberRepository;

    @Autowired
    private OrganizationRepo organizationRepo;
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
    public Page<Member> getALL(Pageable pageable, String matricule, Fonction fonction,
                               STATUS printStatus, Grade grade, Long organisationId,
                               STATUS licenceStatus, String from , String to) throws MemberException {

            return null;
            }

}
