package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Dtos.Licences.LicencePricedto;
import com.fecakarate.backendfecakarate.Exceptions.OrganizationException;
import com.fecakarate.backendfecakarate.Models.LicencePrice;
import com.fecakarate.backendfecakarate.Repository.LicencePriceRepo;
import com.fecakarate.backendfecakarate.Services.interfaces.ILicencePriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILicencePriceImpl implements ILicencePriceService {

    private final LicencePriceRepo licencePriceRepo;

    public ILicencePriceImpl(LicencePriceRepo licencePriceRepo) {
        this.licencePriceRepo = licencePriceRepo;
    }

    @Override
    public LicencePrice add(LicencePricedto licencePricedto) {
        LicencePrice licencePrice = new LicencePrice();
        BeanUtils.copyProperties(licencePrice, licencePrice);
        return licencePriceRepo.save(licencePrice);
    }

    @Override
    public LicencePrice update(Long id,LicencePricedto licencePricedto) {
        LicencePrice  licencePrice =  licencePriceRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("100","PRICE with id ["+id+"] not found!" ));
        BeanUtils.copyProperties(licencePrice, licencePrice);
        return licencePriceRepo.save(licencePrice);
    }

    @Override
    public String delete(Long id) {
        licencePriceRepo.findById(id)
                .orElseThrow(() -> new OrganizationException("100","PRICE with id ["+id+"] not found!" ));
        licencePriceRepo.deleteById(id);
        return "Success";
    }

    @Override
    public List<LicencePrice> getAll() {
        return licencePriceRepo.findAll();
    }
}
