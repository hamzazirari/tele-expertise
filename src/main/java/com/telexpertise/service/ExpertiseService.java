package com.telexpertise.service;

import com.telexpertise.dao.ConsultationDAO;
import com.telexpertise.dao.DemandeExpertiseDAO;
import com.telexpertise.enums.Priorite;
import com.telexpertise.enums.StatutConsultation;
import com.telexpertise.enums.StatutCreneau;
import com.telexpertise.enums.StatutDemande;
import com.telexpertise.model.Consultation;
import com.telexpertise.model.Creneau;
import com.telexpertise.model.DemandeExpertise;
import com.telexpertise.model.Specialiste;
import java.time.LocalDateTime;

public class ExpertiseService {

    private DemandeExpertiseDAO demandeDAO = new DemandeExpertiseDAO();
    private ConsultationDAO consultationDAO = new ConsultationDAO();
    private CreneauService creneauService = new CreneauService();

    public boolean creerDemande(Long consultationId, Specialiste specialiste, Long creneauId,
                                String question, String donneesAnalyses, Priorite priorite) {

        Creneau creneau = creneauService.trouverParId(creneauId);

        if (creneau.getStatut() != StatutCreneau.DISPONIBLE || !creneauService.estFutur(creneau)) {
            return false;
        }

        Consultation consultation = consultationDAO.findById(consultationId);

        DemandeExpertise demande = new DemandeExpertise();
        demande.setConsultation(consultation);
        demande.setSpecialiste(specialiste);
        demande.setCreneau(creneau);
        demande.setQuestion(question);
        demande.setDonneesAnalyses(donneesAnalyses);
        demande.setPriorite(priorite);
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setDateDemande(LocalDateTime.now());
        demandeDAO.save(demande);

        consultation.setStatut(StatutConsultation.EN_ATTENTE_AVIS_SPECIALISTE);
        consultationDAO.update(consultation);

        // Le creneau est réservé
        creneauService.reserver(creneau);

        return true;
    }
}