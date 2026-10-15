package com.telexpertise.dao;

import com.telexpertise.model.Patient;
import com.telexpertise.model.SignesVitaux;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class SignesVitauxDAO {

    public void save(SignesVitaux signesVitaux) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(signesVitaux);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public SignesVitaux findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(SignesVitaux.class, id);
        } finally {
            em.close();
        }
    }

    public List<SignesVitaux> findByPatient(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM SignesVitaux s WHERE s.patient = :patient ORDER BY s.dateMesure DESC",
                            SignesVitaux.class)
                    .setParameter("patient", patient)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}