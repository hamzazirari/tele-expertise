package com.telexpertise;

import com.telexpertise.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // Si cette ligne ne plante pas, la connexion marche
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("Connexion OK !");
        em.close();
        JPAUtil.close();
    }
}