package com.telexpertise.service;

import com.telexpertise.dao.SpecialisteDAO;
import com.telexpertise.enums.Specialite;
import com.telexpertise.model.Specialiste;
import java.util.List;

public class SpecialisteService {

    private SpecialisteDAO specialisteDAO = new SpecialisteDAO();

    // Stream API (dans le DAO) : filtre par spécialité + tri par tarif
    public List<Specialiste> parSpecialite(Specialite specialite) {
        return specialisteDAO.findBySpecialiteTrieParTarif(specialite);
    }
}