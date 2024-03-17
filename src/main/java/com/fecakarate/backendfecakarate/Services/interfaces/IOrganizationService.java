package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.organization.OrganizationDto;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.Organization;
import com.google.zxing.WriterException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IOrganizationService {
    Organization addOrg(OrganizationDto organizationDto) throws OrganizationException, IOException, WriterException;

    Organization updateOrg(OrganizationDto organizationDto) throws OrganizationException;

    Organization getByIdOrg(Long id) throws OrganizationException;

    Page<Organization> getALLOrg(Pageable pageable, String nom, String ville,
                                 String region, String departement, String quartier,
                                 String etat, String printStatus, String from, String to);

    String deleteOrg(Long id) throws OrganizationException;

    List<Organization> getAll();
}
