package com.telexpertise.dao;

import com.telexpertise.model.Patient;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PatientDAO {

    // Ajouter un patient
    public void save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Modifier un patient
    public void update(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(patient);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Chercher par id
    public Patient findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Patient.class, id);
        } finally {
            em.close();
        }
    }

    // Chercher par numéro de sécurité sociale (recherche du patient à l'accueil)
    public Patient findByNumeroSecuriteSociale(String numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Patient> resultat = em
                    .createQuery("SELECT p FROM Patient p WHERE p.numeroSecuriteSociale = :numero", Patient.class)
                    .setParameter("numero", numero)
                    .getResultList();
            if (resultat.isEmpty()) {
                return null;
            }
            return resultat.get(0);
        } finally {
            em.close();
        }
    }

    // Liste de tous les patients
    public List<Patient> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Patient p", Patient.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}