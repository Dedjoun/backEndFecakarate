package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.ClubDto;
import com.fecakarate.backendfecakarate.Dtos.OrganisationDto;
import com.fecakarate.backendfecakarate.Exceptions.ClubException;
import com.fecakarate.backendfecakarate.Models.Organisation;
import com.fecakarate.backendfecakarate.Repository.ClubRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.ClubServices;
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
public class ClubServicesImpl implements ClubServices {

    private final ClubRepo clubRepo;


    @Override
    public Organisation add(OrganisationDto organisationDto) throws ClubException {
        String matricule = RandomUtil.generateRandommatricule(6).toUpperCase();
        Organisation organisation = new Organisation();
        BeanUtils.copyProperties(organisationDto, organisation);
        organisation.setMatricule(matricule);
        return clubRepo.save(organisation);
    }

    @Override
    public Organisation update(OrganisationDto organisationDto) throws ClubException {
        return null;
    }

    @Override
    public Organisation getById(Long id) throws ClubException {
        return clubRepo.findById(id)
                .orElseThrow(() -> new ClubException("400","Club with id ["+id+"] not found!" ));
    }

    @Override
    public Page<Organisation> getALL(Pageable pageable, String nom, String ville, String region, String departement, String quartier, String etat, String printStatus) {
        return null;
    }

    @Override
    public String delete(Long id) throws ClubException {
        clubRepo.findById(id)
                .orElseThrow(() -> new ClubException("400","Club with id ["+id+"] not found!" ));
        clubRepo.deleteById(id);
        return "SUCCESS";
    }
}
