package com.fecakarate.backendfecakarate.Services.implementations;

import com.fecakarate.backendfecakarate.Enums.*;
import com.fecakarate.backendfecakarate.Services.interfaces.IListService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class IListServiceImpl implements IListService {

    @Override
    public List<String> getFonctionSystems() {
        List<String> stringList = new ArrayList<>();
        for (Fonction fonction : Fonction.values()){
            stringList.add(fonction.getDescription());
        }
        return stringList;
    }

    @Override
    public List<String> getStatuSystems() {
        List<String> stringList = new ArrayList<>();
        for (STATUS status : STATUS.values()){
            stringList.add(status.name());
        }
        return stringList;
    }

    @Override
    public List<String> getRegion() {
        List<String> stringList = new ArrayList<>();
        for (Region region : Region.values()){
            stringList.add(region.getDescription());
        }
        return stringList;
    }

    @Override
    public List<String> getDepartement() {
        List<String> stringList = new ArrayList<>();
        for (Departement departement : Departement.values()){
            stringList.add(departement.getDescription());
        }
        return stringList;
    }



    @Override
    public List<String> getGrade() {
        List<String> stringList = new ArrayList<>();
        for (Grade grade : Grade.values()){
            stringList.add(grade.getDescription());
        }
        return stringList;
    }

}
