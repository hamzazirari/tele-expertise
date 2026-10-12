package com.telexpertise.service;

import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.dao.PatientDAO;
import com.telexpertise.dao.SignesVitauxDAO;
import com.telexpertise.model.FileAttente;
import com.telexpertise.model.Patient;
import com.telexpertise.model.SignesVitaux;
import java.time.LocalDateTime;

public class PatientService {

    private PatientDAO patientDAO = new PatientDAO();
    private SignesVitauxDAO signesVitauxDAO = new SignesVitauxDAO();
    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();

    // Recherche par numéro de sécurité sociale (null si introuvable)
    public Patient rechercher(String numeroSecu) {
        return patientDAO.findByNumeroSecuriteSociale(numeroSecu);
    }

    public Patient trouverParId(Long id) {
        return patientDAO.findById(id);
    }

    // Vrai si un patient avec ce numéro existe déjà
    public boolean existe(String numeroSecu) {
        return patientDAO.findByNumeroSecuriteSociale(numeroSecu) != null;
    }

    // Nouveau patient : crée le dossier, les signes vitaux, puis l'ajoute à la file
    public void creerPatientEtAccueillir(Patient patient, SignesVitaux signes) {
        patientDAO.save(patient);
        accueillir(patient, signes);
    }

    // Patient existant : nouveaux signes vitaux + ajout à la file
    public void accueillir(Patient patient, SignesVitaux signes) {
        signes.setPatient(patient);
        signes.setDateMesure(LocalDateTime.now());
        signesVitauxDAO.save(signes);

        FileAttente file = new FileAttente();
        file.setPatient(patient);
        file.setSignesVitaux(signes);
        file.setHeureArrivee(LocalDateTime.now());
        fileAttenteDAO.save(file);
    }
}