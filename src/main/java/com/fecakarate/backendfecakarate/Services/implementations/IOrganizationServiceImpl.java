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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;


@Service
@Slf4j
public class IOrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepo organizationRepo;
    private final IUserservice userservice;
    private final IQRCodeServiceImpl iqrCodeService;

    @Value("${feca.data.name}")
    private String fecaDataName;

    @Value("${feca.data.matricule.len}")
    private int fecaMatriculeLen;

    @Value("${feca.data.ext.img}")
    private String extImg;

    @Value("${feca.data.url}")
    private String fecaUrl;

    @Value("${feca.data.defauld.Img}")
    private String defauldImg;

    @Value("${feca.data.defauldPassword}")
    private String defauldPassword;

    @Value("${feca.data.contry.iso}")
    private String defauldContryISO;

    @Value("${feca.data.contact.len}")
    private int defauldContactLen;


    IOrganizationServiceImpl(OrganizationRepo organizationRepo, IUserservice userservice, IQRCodeServiceImpl iqrCodeService){
        this.organizationRepo = organizationRepo;
        this.userservice = userservice;
        this.iqrCodeService = iqrCodeService;
    }
    @Override
    @Transactional
    public Organization addOrg(OrganizationDto organizationDto) throws OrganizationException, IOException {

            log.info("Start Register organization to email :{} and Name : {}", organizationDto.getEmail(), organizationDto.getNom().toUpperCase());

            if (organizationRepo.existsByEmail(organizationDto.getEmail())){
                log.warn("the club already exists with this email  ["+organizationDto.getEmail()+"].");
                throw new OrganizationException("100","the club already exists with this email  ["+organizationDto.getEmail()+"].");
            }

            if (organizationRepo.existsByNom(organizationDto.getNom().toUpperCase())){
                log.warn("the club already exists with this email  ["+organizationDto.getNom()+"].");
                throw new OrganizationException("100","the club already exists with this email  ["+organizationDto.getNom()+"].");
            }

            organizationDto.setMatricule(fecaDataName+RandomUtil.generateRandommatricule(fecaMatriculeLen).toUpperCase());

            //GENERATION DU QRCODE
            String link = fecaUrl + organizationDto.getMatricule();
            iqrCodeService.generateQRCode(QrcodeDest.Club,link, organizationDto.getMatricule());
            String fileName = organizationDto.getMatricule()+extImg;

            //set start status
            organizationDto.setNom(organizationDto.getNom().toUpperCase());
            organizationDto.setResponsable(organizationDto.getResponsable().toUpperCase());
            organizationDto.setUserType(UserType.ORGANIZATION); //Organization type
            organizationDto.setLogo(defauldImg); //start value icon
            organizationDto.setPrintStatus(STATUS.PENDING_PRINT); //value for print attestation
            organizationDto.setEtat(STATUS.PENDING_ACTIVE); //value for account status
            organizationDto.setLicenceStatus(STATUS.LicenceNonPaie); //value for status of attestation paie
            organizationDto.setQrcode(fileName);

            //Control Contact
            if (organizationDto.getContact().length() == defauldContactLen){
                String phone = defauldContryISO+organizationDto.getContact();
                organizationDto.setContact(phone);
            }


            //Save User
            Users users = new Users();
            users.setFirstname(organizationDto.getNom());
            users.setLastname(organizationDto.getNom());
            users.setEmail(organizationDto.getEmail());
            users.setPassword(defauldPassword);
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
        Organization organization = organizationRepo.findById(organizationDto.getId())
                .orElseThrow(() -> new OrganizationException("404", "Organization with id [" + organizationDto.getId() + "] not found!"));

        updateFieldIfNotNullOrEmpty(organizationDto.getNom(), organization::setNom);
        updateFieldIfNotNull(organizationDto.getPrintStatus(), organization::setPrintStatus);
        updateFieldIfNotNull(organizationDto.getLicenceStatus(), organization::setLicenceStatus);
        updateFieldIfNotNullOrEmpty(organizationDto.getVille(), organization::setVille);
        updateFieldIfNotNullOrEmpty(organizationDto.getContact(), organization::setContact);
        updateFieldIfNotNullOrEmpty(organizationDto.getResponsable(), value -> organization.setResponsable(value.toUpperCase()));
        updateFieldIfNotNullOrEmpty(organizationDto.getRegion(), organization::setRegion);
        updateFieldIfNotNullOrEmpty(organizationDto.getDepartement(), organization::setDepartement);
        updateFieldIfNotNullOrEmpty(organizationDto.getArrondissement(), organization::setArrondissement);
        updateFieldIfNotNullOrEmpty(organizationDto.getQuartier(), value -> organization.setQuartier(value.toUpperCase()));

        return organizationRepo.save(organization);
    }

    private <T> void updateFieldIfNotNullOrEmpty(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
    @Override
    public Organization getByIdOrg(Long id) throws OrganizationException {
        return organizationRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("400","Organization with id ["+id+"] not found!" ));
    }
    @Override
    public Page<Organization> getALLOrg(Pageable pageable, String nom, String ville, String region, String departement, String quartier,
                                        String etat, String printStatus, String from, String to) {

        return organizationRepo.findAll((Specification<Organization>) (root, query, criteriaBuilder) ->
                        buildPredicate(root, criteriaBuilder, nom, ville, region, departement, quartier, etat, printStatus, from, to),
                pageable);
    }

    private <Criteriabuilder> Predicate buildPredicate(Root<Organization> root, Criteriabuilder criteriaBuilder, String nom, String ville, String region, String departement,
                                                       String quartier, String etat, String printStatus, String from, String to) {
        List<Predicate> predicates = new ArrayList<>();

        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("nom"), nom);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("ville"), ville);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("region"), region);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("departement"), departement);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("quartier"), quartier);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("etat"), etat);
        addLikePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("printStatus"), printStatus);

        addDatePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("createdDate"), from, true);
        addDatePredicateIfNotNullOrEmpty(predicates, (jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder, root.get("createdDate"), to, false);

        return ((jakarta.persistence.criteria.CriteriaBuilder) criteriaBuilder).and(predicates.toArray(new Predicate[0]));
    }

    private void addLikePredicateIfNotNullOrEmpty(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Path<String> path, String value) {
        if (value != null && !value.isEmpty()) {
            predicates.add(criteriaBuilder.like(path, "%" + value + "%"));
        }
    }

    private void addDatePredicateIfNotNullOrEmpty(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Path<LocalDate> path, String value, boolean isGreaterThanOrEqual) {
        if (value != null && !value.isEmpty()) {
            LocalDate date = LocalDate.parse(value); // Suppose que la date est au format ISO-8601 (YYYY-MM-DD)
            if (isGreaterThanOrEqual) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, date));
            } else {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(path, date));
            }
        }
    }

    @Transactional
    @Override
    public String deleteOrg(Long id) throws OrganizationException {
        if (!organizationRepo.existsById(id)) {
            throw new OrganizationException("400", "Organization with id [" + id + "] not found!");
        }
        organizationRepo.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public List<Organization> getAll() {
        return organizationRepo.findAll();
    }
}
