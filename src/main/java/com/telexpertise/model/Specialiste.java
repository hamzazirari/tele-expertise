package com.telexpertise.model;

import com.telexpertise.enums.Specialite;
import jakarta.persistence.*;

@Entity
public class Specialiste extends Utilisateur {

    @Enumerated(EnumType.STRING)
    private Specialite specialite;

    // Tarif pour une réservation (en DH)
    private Double tarif;

    // Durée moyenne de consultation : fixe, 30 minutes (brief)
    private Integer dureeConsultation = 30;

    public Specialiste() {
    }

    public Specialite getSpecialite() { return specialite; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }

    public Double getTarif() { return tarif; }
    public void setTarif(Double tarif) { this.tarif = tarif; }

    public Integer getDureeConsultation() { return dureeConsultation; }
    public void setDureeConsultation(Integer dureeConsultation) { this.dureeConsultation = dureeConsultation; }
}