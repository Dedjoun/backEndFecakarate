package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface IOrganizationService {
    Organization add(OrganizationDto organizationDto) throws OrganizationException, IOException, WriterException;

    Organization update(OrganizationDto organizationDto) throws OrganizationException;

    Organization getById(Long id) throws OrganizationException;

    Page<Organization> getALL(Pageable pageable, String nom, String ville,
                              String region, String departement, String quartier,
                              String etat, String printStatus);

    String delete(Long id) throws OrganizationException;
}
