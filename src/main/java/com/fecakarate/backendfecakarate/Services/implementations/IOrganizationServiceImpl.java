package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Enums.STATUS;
import com.fecakarate.backendfecakarate.Enums.UserType;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.fecakarate.backendfecakarate.Models.Users;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;
import com.fecakarate.backendfecakarate.Repository.UserRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganizationService;
import com.fecakarate.backendfecakarate.Services.interfaces.IUserservice;
import com.fecakarate.backendfecakarate.Utils.RandomUtil;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;


@Service
@RequiredArgsConstructor
@Slf4j
public class IOrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepo organizationRepo;

    private final IUserservice userservice;

    private final IQRCodeServiceImpl iqrCodeService;

    private final UserRepo userRepo;

    @Override
    public Organization add(OrganizationDto organizationDto) throws OrganizationException, IOException, WriterException {


            //save organization
            log.info("Start Register organization to email :{}", organizationDto.getEmail());
            if (organizationRepo.existsByEmail(organizationDto.getEmail())){
                log.warn("the club already exists with this email  ["+organizationDto.getEmail()+"].");
                throw new OrganizationException("100","the club already exists with this email  ["+organizationDto.getEmail()+"].");
            }

            organizationDto.setMatricule(RandomUtil.generateRandommatricule(6).toUpperCase());

            //GENERATION DU QRCODE
            String link = "www.fecakarate.com/" + organizationDto.getMatricule();
            iqrCodeService.generateQRCode(link, organizationDto.getMatricule());
            String fileName = organizationDto.getMatricule()+".png";

            //set start status
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
            Users users = new Users(organizationDto.getEmail(),organizationDto.getMatricule(),organizationDto.getNom(),"1234567890",new HashSet<>());
            Users users1 =  userservice.saveUser(users);
            log.info("CREATE USER FOR ORGANIZATION UserName:{}  Matricule:{}  Name:{}", users1.getEmail(), users1.getMatricule(), users1.getName());

            //Assign Role to organization
            userservice.addToUser(organizationDto.getEmail(),"Role_ORG");
            Users users2 = userRepo.findByEmail(organizationDto.getEmail()).orElseThrow();
            log.info("User Information's {}",users2);

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization);
        organization.setCreatedBy(organizationDto.getEmail());
        organization.setCreatedDate(new Date());

        return organizationRepo.save(organization);

    }

    @Override
    public Organization update(OrganizationDto organizationDto) throws OrganizationException {
        return null;
    }

    @Override
    public Organization getById(Long id) throws OrganizationException {
        return organizationRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("400","Club with id ["+id+"] not found!" ));
    }

    @Override
    public Page<Organization> getALL(Pageable pageable, String nom, String ville, String region, String departement, String quartier, String etat, String printStatus) {
        return null;
    }

    @Override
    public String delete(Long id) throws OrganizationException {
        organizationRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("400","Club with id ["+id+"] not found!" ));
        organizationRepo.deleteById(id);
        return "SUCCESS";
    }
}
