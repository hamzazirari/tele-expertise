package com.telexpertise.dao;

import com.telexpertise.model.FileAttente;
import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileAttenteDAO {

    public void save(FileAttente fileAttente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fileAttente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(FileAttente fileAttente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(fileAttente) ? fileAttente : em.merge(fileAttente));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public FileAttente findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(FileAttente.class, id);
        } finally {
            em.close();
        }
    }

    public List<FileAttente> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT f FROM FileAttente f", FileAttente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Stream API : patients d'une date donnée, triés du plus ancien au plus récent
    public List<FileAttente> findByDate(LocalDate date) {
        return findAll().stream()
                .filter(f -> f.getHeureArrivee().toLocalDate().equals(date))
                .sorted(Comparator.comparing(FileAttente::getHeureArrivee))
                .collect(Collectors.toList());
    }
}