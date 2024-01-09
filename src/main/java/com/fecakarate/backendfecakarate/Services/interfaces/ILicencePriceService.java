package com.fecakarate.backendfecakarate.Services.interfaces;

import com.fecakarate.backendfecakarate.Dtos.Licences.LicencePricedto;
import com.fecakarate.backendfecakarate.Models.LicencePrice;

import java.util.List;

public interface ILicencePriceService {
    LicencePrice add(LicencePricedto licencePricedto);

    LicencePrice update(Long id,LicencePricedto licencePricedto);

    String delete(Long id);

    List<LicencePrice> getAll();

}
