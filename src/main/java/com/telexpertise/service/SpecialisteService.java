package com.telexpertise.service;

import com.telexpertise.dao.SpecialisteDAO;
import com.telexpertise.enums.Specialite;
import com.telexpertise.model.Specialiste;
import java.util.List;

public class SpecialisteService {

    private SpecialisteDAO specialisteDAO = new SpecialisteDAO();

    public List<Specialiste> parSpecialite(Specialite specialite) {
        return specialisteDAO.findBySpecialiteTrieParTarif(specialite);
    }
    public Specialiste trouverParId(Long id) {
        return specialisteDAO.findById(id);
    }

}