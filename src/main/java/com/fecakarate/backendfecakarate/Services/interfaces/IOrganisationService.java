package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.OrganisationDto;
import com.fecakarate.backendfecakarate.Exceptions.ClubException;
import com.fecakarate.backendfecakarate.Models.Organisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrganisationService {
    Organisation add(OrganisationDto organisationDto) throws ClubException;

    Organisation update(OrganisationDto organisationDto) throws ClubException;

    Organisation getById(Long id) throws ClubException;

    Page<Organisation> getALL(Pageable pageable, String nom, String ville,
                              String region, String departement, String quartier,
                              String etat, String printStatus);

    String delete(Long id) throws ClubException;
}
