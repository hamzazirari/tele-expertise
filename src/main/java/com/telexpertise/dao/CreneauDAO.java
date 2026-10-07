package com.telexpertise.dao;

import com.telexpertise.model.Creneau;
import com.telexpertise.model.Specialiste;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CreneauDAO {

    public void save(Creneau creneau) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(creneau);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Modifier un créneau (ex : réservé -> INDISPONIBLE, annulé -> DISPONIBLE)
    public void update(Creneau creneau) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(creneau);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Creneau findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Creneau.class, id);
        } finally {
            em.close();
        }
    }

    // Tous les créneaux d'un spécialiste, triés par date puis heure
    public List<Creneau> findBySpecialiste(Specialiste specialiste) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT c FROM Creneau c WHERE c.specialiste = :specialiste "
                                    + "ORDER BY c.date, c.heureDebut", Creneau.class)
                    .setParameter("specialiste", specialiste)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}