package com.fecakarate.backendfecakarate.Services.mapper;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Models.Member;
import com.fecakarate.backendfecakarate.Repository.MemberRepo;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;

public class MemberServiceMapper {

    private final MemberRepo memberRepository;
    private final OrganizationRepo organizationRepo;

    public MemberServiceMapper(MemberRepo memberRepository, OrganizationRepo organizationRepo) {
        this.memberRepository = memberRepository;
        this.organizationRepo = organizationRepo;
    }

    public void updateMember(MemberDto memberDto) {
        Member member = memberRepository.findByMatricule(memberDto.getMatricule());
        member.setMatricule(memberDto.getMatricule());
        member.setNom(memberDto.getNom());
        member.setPrenom(memberDto.getPrenom());
        member.setDateNaissance(memberDto.getDateNaissance());
        member.setLieuNaissance(memberDto.getLieuNaissance());
        member.setOrganization(memberDto.getOrganisationId() != null ? organizationRepo.findById(memberDto.getOrganisationId()).get() : null);
        member.setGrade(memberDto.getGrade());
        member.setFonction(memberDto.getFonction());
        member.setQrcode(memberDto.getQrcode());
        member.setPhoto(memberDto.getPhoto());
        member.setPrintStatus(memberDto.getPrintStatus());
        member.setLicenceStatus(memberDto.getLicenceStatus());

        memberRepository.save(member);
    }
}
