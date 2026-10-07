package com.telexpertise.dao;

import com.telexpertise.enums.Specialite;
import com.telexpertise.model.Specialiste;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialisteDAO {

    public void save(Specialiste specialiste) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(specialiste);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Modifier le profil (tarif, spécialité)
    public void update(Specialiste specialiste) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(specialiste);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Specialiste findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Specialiste.class, id);
        } finally {
            em.close();
        }
    }

    public List<Specialiste> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM Specialiste s", Specialiste.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Stream API : filtre par spécialité + tri par tarif (du moins cher au plus cher)
    public List<Specialiste> findBySpecialiteTrieParTarif(Specialite specialite) {
        return findAll().stream()
                .filter(s -> s.getSpecialite() == specialite)
                .sorted(Comparator.comparing(Specialiste::getTarif))
                .collect(Collectors.toList());
    }
}