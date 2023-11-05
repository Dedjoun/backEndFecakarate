package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.ClubException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.fecakarate.backendfecakarate.Repository.OrganizationRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.IOrganisationService;
import com.fecakarate.backendfecakarate.Utils.RandomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class IOrganisationServiceImpl implements IOrganisationService {

    private final OrganizationRepo organizationRepo;


    @Override
    public Organization add(OrganizationDto organizationDto) throws ClubException {
        String matricule = RandomUtil.generateRandommatricule(6).toUpperCase();
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization);
        organization.setMatricule(matricule);
        return organizationRepo.save(organization);
    }

    @Override
    public Organization update(OrganizationDto organizationDto) throws ClubException {
        return null;
    }

    @Override
    public Organization getById(Long id) throws ClubException {
        return organizationRepo.findById(id)
                .orElseThrow(() -> new ClubException("400","Club with id ["+id+"] not found!" ));
    }

    @Override
    public Page<Organization> getALL(Pageable pageable, String nom, String ville, String region, String departement, String quartier, String etat, String printStatus) {
        return null;
    }

    @Override
    public String delete(Long id) throws ClubException {
        organizationRepo.findById(id)
                .orElseThrow(() -> new ClubException("400","Club with id ["+id+"] not found!" ));
        organizationRepo.deleteById(id);
        return "SUCCESS";
    }
}
