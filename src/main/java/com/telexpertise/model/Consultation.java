package com.telexpertise.model;

import com.telexpertise.enums.StatutConsultation;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateConsultation;

    private String motif;
    private String observations;

    // Remplis seulement en prise en charge directe (scénario A)
    private String diagnostic;
    private String traitement;

    // Coût fixe de la consultation : 150 DH (brief)
    private Double cout = 150.0;

    @Enumerated(EnumType.STRING)
    private StatutConsultation statut = StatutConsultation.EN_COURS;

    // Le patient consulté
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Le médecin généraliste qui fait la consultation
    @ManyToOne
    @JoinColumn(name = "generaliste_id", nullable = false)
    private Utilisateur generaliste;

    public Consultation() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateConsultation() { return dateConsultation; }
    public void setDateConsultation(LocalDateTime dateConsultation) { this.dateConsultation = dateConsultation; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }

    public String getTraitement() { return traitement; }
    public void setTraitement(String traitement) { this.traitement = traitement; }

    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }

    public StatutConsultation getStatut() { return statut; }
    public void setStatut(StatutConsultation statut) { this.statut = statut; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Utilisateur getGeneraliste() { return generaliste; }
    public void setGeneraliste(Utilisateur generaliste) { this.generaliste = generaliste; }
}