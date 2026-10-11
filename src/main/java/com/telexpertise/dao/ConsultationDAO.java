package com.telexpertise.dao;

import com.telexpertise.model.Consultation;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;
import com.telexpertise.model.Utilisateur;
import com.telexpertise.enums.StatutConsultation;

public class ConsultationDAO {

    public void save(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(consultation);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Modifier (changer le statut, ajouter diagnostic/traitement...)
    public void update(Consultation consultation) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(consultation);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Consultation findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Consultation.class, id);
        } finally {
            em.close();
        }
    }

    public List<Consultation> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consultation c", Consultation.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Les consultations d'un généraliste
    public List<Consultation> findByGeneraliste(Utilisateur generaliste) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Consultation c WHERE c.generaliste = :g ORDER BY c.dateConsultation DESC",
                            Consultation.class)
                    .setParameter("g", generaliste)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Les consultations d'un généraliste, filtrées par statut (Stream API)
    public List<Consultation> findByGeneralisteEtStatut(Utilisateur generaliste, StatutConsultation statut) {
        return findByGeneraliste(generaliste).stream()
                .filter(c -> c.getStatut() == statut)
                .collect(java.util.stream.Collectors.toList());
    }
}