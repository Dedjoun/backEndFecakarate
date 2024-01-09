package com.fecakarate.backendfecakarate.Repository;

import com.fecakarate.backendfecakarate.Models.LicencePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencePriceRepo extends JpaRepository<LicencePrice,Long>, JpaSpecificationExecutor<LicencePrice> {
}
