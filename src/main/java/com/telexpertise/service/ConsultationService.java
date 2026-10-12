package com.telexpertise.service;

import com.telexpertise.dao.ConsultationDAO;
import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.enums.StatutConsultation;
import com.telexpertise.model.Consultation;
import com.telexpertise.model.FileAttente;
import com.telexpertise.model.Utilisateur;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultationService {

    private static final double COUT_CONSULTATION = 150.0;

    private ConsultationDAO consultationDAO = new ConsultationDAO();
    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();

    // US1 : créer la consultation depuis la file d'attente, puis retirer le patient de la file
    public void creerConsultation(Long fileId, Utilisateur generaliste, String motif, String observations) {
        FileAttente file = fileAttenteDAO.findById(fileId);

        Consultation consultation = new Consultation();
        consultation.setPatient(file.getPatient());
        consultation.setGeneraliste(generaliste);
        consultation.setMotif(motif);
        consultation.setObservations(observations);
        consultation.setDateConsultation(LocalDateTime.now());
        consultation.setCout(COUT_CONSULTATION);
        consultation.setStatut(StatutConsultation.EN_COURS);
        consultationDAO.save(consultation);

        fileAttenteDAO.delete(file);
    }

    public Consultation trouverParId(Long id) {
        return consultationDAO.findById(id);
    }

    // Consultations ouvertes (pas encore TERMINEE) d'un généraliste
    public List<Consultation> consultationsOuvertes(Utilisateur generaliste) {
        return consultationDAO.findByGeneraliste(generaliste).stream()
                .filter(c -> c.getStatut() != StatutConsultation.TERMINEE)
                .collect(Collectors.toList());
    }

    // Scénario A : diagnostic + traitement + clôture
    public void cloturer(Long id, String diagnostic, String traitement) {
        Consultation consultation = consultationDAO.findById(id);
        consultation.setDiagnostic(diagnostic);
        consultation.setTraitement(traitement);
        consultation.setStatut(StatutConsultation.TERMINEE);
        consultationDAO.update(consultation);
    }

    // Scénario B : la consultation reste ouverte, en attente de l'avis
    public void demanderAvisSpecialiste(Long id) {
        Consultation consultation = consultationDAO.findById(id);
        consultation.setStatut(StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE);
        consultationDAO.update(consultation);
    }
}