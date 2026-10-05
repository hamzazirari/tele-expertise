package com.telexpertise.dao;

import com.telexpertise.model.Utilisateur;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UtilisateurDAO {

    // Ajouter un utilisateur
    public void save(Utilisateur utilisateur) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utilisateur);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Chercher par id
    public Utilisateur findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Utilisateur.class, id);
        } finally {
            em.close();
        }
    }

    // Chercher par email (utile pour le login)
    public Utilisateur findByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Utilisateur> resultat = em
                    .createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getResultList();
            if (resultat.isEmpty()) {
                return null;
            }
            return resultat.get(0);
        } finally {
            em.close();
        }
    }

    // Liste de tous les utilisateurs
    public List<Utilisateur> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}