package com.telexpertise.model;

import com.telexpertise.enums.TypeActe;
import jakarta.persistence.*;

@Entity
@Table(name = "acte_technique")
public class ActeTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeActe type;

    // Le brief ne donne pas de prix : on le saisit nous-mêmes (en DH)
    private Double prix;

    // La consultation pour laquelle l'acte est réalisé
    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    public ActeTechnique() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TypeActe getType() { return type; }
    public void setType(TypeActe type) { this.type = type; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public Consultation getConsultation() { return consultation; }
    public void setConsultation(Consultation consultation) { this.consultation = consultation; }
}