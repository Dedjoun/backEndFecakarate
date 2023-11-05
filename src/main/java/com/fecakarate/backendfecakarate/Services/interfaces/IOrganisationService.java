package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.ClubException;
import com.fecakarate.backendfecakarate.Models.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrganisationService {
    Organization add(OrganizationDto organizationDto) throws ClubException;

    Organization update(OrganizationDto organizationDto) throws ClubException;

    Organization getById(Long id) throws ClubException;

    Page<Organization> getALL(Pageable pageable, String nom, String ville,
                              String region, String departement, String quartier,
                              String etat, String printStatus);

    String delete(Long id) throws ClubException;
}
