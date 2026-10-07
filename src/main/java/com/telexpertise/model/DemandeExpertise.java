package com.telexpertise.model;

import com.telexpertise.enums.Priorite;
import com.telexpertise.enums.StatutDemande;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demande_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDemande;

    // Rempli par le généraliste
    private String question;
    private String donneesAnalyses;   // données et analyses fournies

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut = StatutDemande.EN_ATTENTE;

    // Rempli par le spécialiste quand il répond
    private String avisMedical;
    private String recommandations;

    // La consultation liée à cette demande
    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    // Le spécialiste choisi
    @ManyToOne
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    // Le créneau réservé
    @OneToOne
    @JoinColumn(name = "creneau_id")
    private Creneau creneau;

    public DemandeExpertise() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDateTime dateDemande) { this.dateDemande = dateDemande; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getDonneesAnalyses() { return donneesAnalyses; }
    public void setDonneesAnalyses(String donneesAnalyses) { this.donneesAnalyses = donneesAnalyses; }

    public Priorite getPriorite() { return priorite; }
    public void setPriorite(Priorite priorite) { this.priorite = priorite; }

    public StatutDemande getStatut() { return statut; }
    public void setStatut(StatutDemande statut) { this.statut = statut; }

    public String getAvisMedical() { return avisMedical; }
    public void setAvisMedical(String avisMedical) { this.avisMedical = avisMedical; }

    public String getRecommandations() { return recommandations; }
    public void setRecommandations(String recommandations) { this.recommandations = recommandations; }

    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }

    public Specialiste getSpecialiste() { return specialiste; }
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }

    public Creneau getCreneau() { return creneau; }
    public void setCreneau(Creneau creneau) { this.creneau = creneau; }
}