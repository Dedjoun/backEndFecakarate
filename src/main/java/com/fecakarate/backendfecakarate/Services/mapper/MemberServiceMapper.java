package com.fecakarate.backendfecakarate.Services.mapper;

import com.fecakarate.backendfecakarate.Dtos.MemberDto;
import com.fecakarate.backendfecakarate.Models.Member;
import com.fecakarate.backendfecakarate.Repository.MemberRepo;
import com.fecakarate.backendfecakarate.Repository.OrganisationRepo;

public class MemberServiceMapper {

    private final MemberRepo memberRepository;
    private final OrganisationRepo organisationRepo;

    public MemberServiceMapper(MemberRepo memberRepository, OrganisationRepo organisationRepo) {
        this.memberRepository = memberRepository;
        this.organisationRepo = organisationRepo;
    }

    public void updateMember(MemberDto memberDto) {
        Member member = memberRepository.findByMatricule(memberDto.getMatricule());
        member.setMatricule(memberDto.getMatricule());
        member.setNom(memberDto.getNom());
        member.setPrenom(memberDto.getPrenom());
        member.setDateNaissance(memberDto.getDateNaissance());
        member.setLieuNaissance(memberDto.getLieuNaissance());
        member.setOrganisation(memberDto.getOrganisationId() != null ? organisationRepo.findById(memberDto.getOrganisationId()).get() : null);
        member.setGrade(memberDto.getGrade());
        member.setFonction(memberDto.getFonction());
        member.setQrcode(memberDto.getQrcode());
        member.setPhoto(memberDto.getPhoto());
        member.setPrintStatus(memberDto.getPrintStatus());
        member.setLicenceStatus(memberDto.getLicenceStatus());

        memberRepository.save(member);
    }
}
