package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Exceptions.MemberException;
import com.fecakarate.backendfecakarate.Models.Member;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IMemberServices {

    Member addMember(MemberDto memberDto) throws MemberException, IOException, WriterException;

    Member updateMember(MemberDto memberDto)throws MemberException;

    Member getByIdMember(Long id) throws MemberException;

    String deleteMember(Long id) throws MemberException;
    Page<Member> getALLMember(Pageable pageable, String matricule, Fonction fonction,
                              STATUS printStatus, Grade grade, Long organisationId,
                              STATUS licenceStatus, String sexe, String region, String departement, String from, String to) throws MemberException;

    List<Member> getAll();
}
