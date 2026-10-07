package com.telexpertise.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_attente")
public class FileAttente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Heure d'arrivée du patient (sert au tri et au filtre par date)
    private LocalDateTime heureArrivee;

    // Le patient qui attend
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Les signes vitaux mesurés lors de cet accueil
    @OneToOne
    @JoinColumn(name = "signes_vitaux_id")
    private SignesVitaux signesVitaux;

    public FileAttente() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(LocalDateTime heureArrivee) { this.heureArrivee = heureArrivee; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public SignesVitaux getSignesVitaux() { return signesVitaux; }
    public void setSignesVitaux(SignesVitaux signesVitaux) { this.signesVitaux = signesVitaux; }
}