package com.telexpertise.dao;

import com.telexpertise.enums.Priorite;
import com.telexpertise.enums.StatutDemande;
import com.telexpertise.model.DemandeExpertise;
import com.telexpertise.model.Specialiste;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class DemandeExpertiseDAO {

    public void save(DemandeExpertise demande) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(demande);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Modifier (le spécialiste ajoute son avis et marque TERMINEE)
    public void update(DemandeExpertise demande) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(demande);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public DemandeExpertise findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(DemandeExpertise.class, id);
        } finally {
            em.close();
        }
    }

    // Toutes les demandes reçues par un spécialiste
    public List<DemandeExpertise> findBySpecialiste(Specialiste specialiste) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT d FROM DemandeExpertise d WHERE d.specialiste = :s ORDER BY d.dateDemande DESC",
                            DemandeExpertise.class)
                    .setParameter("s", specialiste)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Stream API : filtre par statut et/ou priorité (null = pas de filtre)
    public List<DemandeExpertise> findByFiltres(Specialiste specialiste, StatutDemande statut, Priorite priorite) {
        return findBySpecialiste(specialiste).stream()
                .filter(d -> statut == null || d.getStatut() == statut)
                .filter(d -> priorite == null || d.getPriorite() == priorite)
                .collect(Collectors.toList());
    }
}