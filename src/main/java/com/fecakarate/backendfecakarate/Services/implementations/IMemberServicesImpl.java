package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.organization.MemberDto;
import com.fecakarate.backendfecakarate.Enums.Fonction;
import com.fecakarate.backendfecakarate.Enums.Grade;
import com.fecakarate.backendfecakarate.Enums.QrcodeDest;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Exceptions.MemberException;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Member;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.fecakarate.backendfecakarate.Repository.MemberRepo;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IMemberServices;
import com.fecakarate.backendfecakarate.Utils.RandomUtil;
import com.google.zxing.WriterException;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class IMemberServicesImpl implements IMemberServices {

    private final MemberRepo memberRepository;
    private final IQRCodeServiceImpl iqrCodeService;
    private final OrganizationRepo organizationRepo;

    public IMemberServicesImpl(MemberRepo memberRepository, IQRCodeServiceImpl iqrCodeService, OrganizationRepo organizationRepo) {
        this.memberRepository = memberRepository;
        this.iqrCodeService = iqrCodeService;
        this.organizationRepo = organizationRepo;
    }

    @Override
    public Member addMember(MemberDto memberDto) throws MemberException, IOException, WriterException {
        Organization organization = organizationRepo.findById(memberDto.getOrganisationId())
                .orElseThrow(() -> new OrganizationException("400","Organization with id ["+memberDto.getOrganisationId()+"] not found!" ));
        Member member = new Member();
        BeanUtils.copyProperties(memberDto, member);
        member.setOrganization(organization);
        member.setLicenceStatus(STATUS.LicenceNonPaie);
        member.setPrintStatus(STATUS.PENDING_PRINT);
        member.setRegion(organization.getRegion());
        member.setDepartement(organization.getDepartement());
        member.setMatricule("FECA"+ RandomUtil.generateRandommatricule(4).toUpperCase());
        String link = "www.fecakarate.com/" + member.getMatricule();
        iqrCodeService.generateQRCode(QrcodeDest.Membre,link, member.getMatricule());
        member.setQrcode(member.getMatricule()+".png");
        if (memberDto.getContact() == null || memberDto.getContact().isEmpty()){
            member.setContact(organization.getContact());
        }
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(MemberDto memberDto) throws MemberException {
    Member member =  memberRepository.findById(memberDto.getOrganisationId())
                .orElseThrow(() -> new MemberException("300","Member with id ["+memberDto.getOrganisationId()+"] not found!" ));


        if (memberDto.getNom() != null && !memberDto.getNom().isEmpty()){
            member.setNom(member.getNom());
        }

        if (memberDto.getPrenom() != null && !memberDto.getPrenom().isEmpty()){
            member.setPrenom(member.getPrenom());
        }

        if (memberDto.getDateNaissance() != null && !memberDto.getDateNaissance().isEmpty()){
            member.setDateNaissance(member.getDateNaissance());
        }

        if (memberDto.getLieuNaissance() != null && !memberDto.getLieuNaissance().isEmpty()){
            member.setLieuNaissance(member.getLieuNaissance());
        }
        if (memberDto.getPrenom() != null && !memberDto.getPrenom().isEmpty()){
            member.setPrenom(member.getPrenom());
        }
        if (memberDto.getOrganisationId() != null){
         Organization organization =   organizationRepo.findById(memberDto.getOrganisationId())
                    .orElseThrow(() -> new OrganizationException("400","Organization with id ["+memberDto.getOrganisationId()+"] not found!" ));
            member.setOrganization(organization);
            member.setContact(organization.getContact());
            member.setRegion(organization.getRegion());
            member.setDepartement(organization.getDepartement());
        }
        if (memberDto.getGrade() != null){
            member.setGrade(member.getGrade());
        }
        if (memberDto.getFonction() != null ){
            member.setFonction(member.getFonction());
        }
        if (memberDto.getPrintStatus() != null ){
            member.setPrintStatus(memberDto.getPrintStatus());
        }
        if (memberDto.getLicenceStatus() != null){
            member.setLicenceStatus(memberDto.getLicenceStatus());
        }

        return memberRepository.save(member);
    }

    @Override
    public Member getByIdMember(Long id) throws MemberException {
        return  memberRepository.findById(id)
                .orElseThrow(() -> new MemberException("300","Member with id ["+id+"] not found!" ));

    }

    @Override
    public String deleteMember(Long id) throws MemberException {
        memberRepository.findById(id)
                .orElseThrow(() -> new MemberException("300","Member with id ["+id+"] not found!" ));
        memberRepository.findById(id);
        return "SUCCESS";
    }

    @Override
    public Page<Member> getALLMember(Pageable pageable, String matricule, Fonction fonction,
                                     STATUS printStatus, Grade grade, Long organisationId,
                                     STATUS licenceStatus, String sexe, String region, String departement, String from, String to) throws MemberException {
        return memberRepository.findAll((Specification<Member>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (matricule != null && !matricule.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("matricule"), "%"+matricule.toUpperCase()+"%")));
            }

            if (region != null && !region.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("region"), "%"+region+"%")));
            }

            if (sexe != null && !sexe.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("sexe"), sexe)));
            }

            if (departement != null && !departement.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("departement"), "%"+departement+"%")));
            }

            if (fonction != null ){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("fonction"), fonction)));
            }

            if (printStatus != null ){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("printStatus"), printStatus)));
            }

            if (grade != null ){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("grade"), grade)));
            }

            if (organisationId != null){
                Optional<Organization> organization = organizationRepo.findById(organisationId);
                organization.ifPresent(value -> predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("organization"), value))));
            }

            if (licenceStatus != null ){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("licenceStatus"), licenceStatus)));
            }

            if (printStatus != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("printStatus"), printStatus)));
            }

            if (printStatus != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("printStatus"), printStatus)));
            }

            if (from != null && !from.isEmpty()) {
                Date dateFrom ;
                try {
                    dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),  dateFrom)));
            }
            if (to != null && !to.isEmpty()) {
                Date dateTo;
                try {
                    dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"),  dateTo)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

            }

}
