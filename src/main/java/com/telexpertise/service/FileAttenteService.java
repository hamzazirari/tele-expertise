package com.telexpertise.service;

import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.model.FileAttente;
import java.time.LocalDate;
import java.util.List;

public class FileAttenteService {

    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();

    public List<FileAttente> patientsDuJour() {
        return fileAttenteDAO.findByDate(LocalDate.now());
    }

    public FileAttente trouverParId(Long id) {
        return fileAttenteDAO.findById(id);
    }
}