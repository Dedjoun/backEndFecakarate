package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Enums.QrcodeDest;
import com.fecakarate.backendfecakarate.Enums.Role;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Enums.UserType;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import com.fecakarate.backendfecakarate.Utils.RandomUtil;
import com.google.zxing.WriterException;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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


@Service
@Slf4j
public class IOrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepo organizationRepo;
    private final IUserservice userservice;
    private final IQRCodeServiceImpl iqrCodeService;



    IOrganizationServiceImpl(OrganizationRepo organizationRepo, IUserservice userservice, IQRCodeServiceImpl iqrCodeService){
        this.organizationRepo = organizationRepo;
        this.userservice = userservice;
        this.iqrCodeService = iqrCodeService;
    }
    @Override
    @Transactional
    public Organization addOrg(OrganizationDto organizationDto) throws OrganizationException, IOException, WriterException {

            log.info("Start Register organization to email :{} and Name : {}", organizationDto.getEmail(), organizationDto.getNom().toUpperCase());
            if (organizationRepo.existsByEmail(organizationDto.getEmail())){
                log.warn("the club already exists with this email  ["+organizationDto.getEmail()+"].");
                throw new OrganizationException("100","the club already exists with this email  ["+organizationDto.getEmail()+"].");
            }

            if (organizationRepo.existsByNom(organizationDto.getNom().toUpperCase())){
                log.warn("the club already exists with this email  ["+organizationDto.getNom()+"].");
                throw new OrganizationException("100","the club already exists with this email  ["+organizationDto.getNom()+"].");
            }

            organizationDto.setMatricule("FECA"+RandomUtil.generateRandommatricule(6).toUpperCase());

            //GENERATION DU QRCODE
            String link = "www.fecakarateAPP2.0.com/" + organizationDto.getMatricule();
            iqrCodeService.generateQRCode(QrcodeDest.Club,link, organizationDto.getMatricule());
            String fileName = organizationDto.getMatricule()+".png";

            //set start status
            organizationDto.setNom(organizationDto.getNom().toUpperCase());
            organizationDto.setResponsable(organizationDto.getResponsable().toUpperCase());
            organizationDto.setUserType(UserType.ORGANIZATION); //Organization type
            organizationDto.setLogo("logo.png"); //start value icon
            organizationDto.setPrintStatus(STATUS.PENDING_PRINT); //value for print attestation
            organizationDto.setEtat(STATUS.PENDING_ACTIVE); //value for account status
            organizationDto.setLicenceStatus(STATUS.LicenceNonPaie); //value for status of attestation paie
            organizationDto.setQrcode(fileName);

            //Control Contact
            if (organizationDto.getContact().length() == 9){
                String phone = "237"+organizationDto.getContact();
                organizationDto.setContact(phone);
            }


            //Save User
            Users users = new Users();
            users.setFirstname(organizationDto.getNom());
            users.setLastname(organizationDto.getNom());
            users.setEmail(organizationDto.getEmail());
            users.setPassword("1234567890");
            users.setRole(Role.User);
            users =  userservice.saveUser(users);
            log.info("CREATE USER FOR ORGANIZATION UserName:{}  Name:{}  ", users.getEmail(), users.getFirstname());


        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization);
        organization.setCreatedBy(organizationDto.getEmail());
        organization.setCreatedDate(new Date());

        return organizationRepo.save(organization);

    }
    @Override
    public Organization updateOrg(OrganizationDto organizationDto) throws OrganizationException {
         Organization organization= organizationRepo.findById(organizationDto.getId())
                    .orElseThrow(() -> new OrganizationException("404","Organization with id ["+organizationDto.getId()+"] not found!" ));

         if (organizationDto.getNom() != null && !organizationDto.getNom().isEmpty()){
             organization.setNom(organizationDto.getNom());
         }

         if (organizationDto.getPrintStatus() != null){
             organization.setPrintStatus(organizationDto.getPrintStatus());
         }

         if (organizationDto.getLicenceStatus() != null){
             organization.setLicenceStatus(organizationDto.getLicenceStatus());
         }

         if (organizationDto.getVille() != null && !organizationDto.getVille().isEmpty()){
             organization.setVille(organizationDto.getVille());
         }

         if (organizationDto.getContact() != null && !organizationDto.getContact().isEmpty()){
             organization.setContact(organizationDto.getContact());
         }

         if (organizationDto.getResponsable() != null && !organizationDto.getResponsable().isEmpty()){
             organization.setResponsable(organizationDto.getResponsable().toUpperCase());
         }

         if (organizationDto.getRegion() != null && !organizationDto.getRegion().isEmpty()){
                organization.setRegion(organizationDto.getRegion());
         }

        if (organizationDto.getDepartement() != null && !organizationDto.getDepartement().isEmpty()){
            organization.setDepartement(organizationDto.getDepartement());
        }
        if (organizationDto.getArrondissement() != null && !organizationDto.getArrondissement().isEmpty()){
            organization.setArrondissement(organizationDto.getArrondissement());
        }

        if (organizationDto.getQuartier() != null && !organizationDto.getQuartier().isEmpty()){
            organization.setQuartier(organizationDto.getQuartier().toUpperCase());
        }
         return organizationRepo.save(organization);
    }
    @Override
    public Organization getByIdOrg(Long id) throws OrganizationException {
        return organizationRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("400","Organization with id ["+id+"] not found!" ));
    }
    @Override
    public Page<Organization> getALLOrg(Pageable pageable, String nom, String ville, String region, String departement, String quartier,
                                        String etat, String printStatus, String from, String to) {

        return organizationRepo.findAll((Specification<Organization>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (nom != null && !nom.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nom"), "%"+nom+"%")));
            }

            if (ville != null && !ville.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("ville"), "%"+ville+"%")));
            }

            if (region != null && !region.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("region"), "%"+region+"%")));
            }

            if (departement != null && !departement.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("departement"), "%"+departement+"%")));
            }

            if (quartier != null && !quartier.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("quartier"), "%"+quartier+"%")));
            }

            if (etat != null && !etat.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("etat"), "%"+etat+"%")));
            }

            if (printStatus != null && !printStatus.isEmpty()){
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("printStatus"), "%"+printStatus+"%")));
            }

            if (from != null && !from.isEmpty()) {
                Date dateFrom;
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
    @Override
    @Transactional
    public String deleteOrg(Long id) throws OrganizationException {
        organizationRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("400","Organization with id ["+id+"] not found!" ));
        organizationRepo.deleteById(id);
        return "SUCCESS";
    }
}
