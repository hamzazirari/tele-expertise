package com.telexpertise.model;

import com.telexpertise.enums.StatutCreneau;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "creneau")
public class Creneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime heureDebut;   // exemple : 09:00
    private LocalTime heureFin;     // exemple : 09:30

    @Enumerated(EnumType.STRING)
    private StatutCreneau statut = StatutCreneau.DISPONIBLE;

    // Plusieurs créneaux pour un seul spécialiste
    @ManyToOne
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    public Creneau() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    public StatutCreneau getStatut() { return statut; }
    public void setStatut(StatutCreneau statut) { this.statut = statut; }

    public Specialiste getSpecialiste() { return specialiste; }
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }
}