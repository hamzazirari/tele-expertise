package com.telexpertise.util;

import com.telexpertise.dao.SpecialisteDAO;
import com.telexpertise.dao.UtilisateurDAO;
import com.telexpertise.enums.Role;
import com.telexpertise.enums.Specialite;
import com.telexpertise.model.Specialiste;
import com.telexpertise.model.Utilisateur;

public class InitData {

    public static void main(String[] args) {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        SpecialisteDAO specialisteDAO = new SpecialisteDAO();

        // 1. Infirmier
        if (utilisateurDAO.findByEmail("infirmier@test.com") == null) {
            Utilisateur infirmier = new Utilisateur();
            infirmier.setNom("Alami");
            infirmier.setPrenom("Sara");
            infirmier.setEmail("infirmier@test.com");
            infirmier.setMotDePasse(PasswordUtil.hasher("1234"));
            infirmier.setRole(Role.INFIRMIER);
            utilisateurDAO.save(infirmier);
        }

        // 2. Généraliste
        if (utilisateurDAO.findByEmail("generaliste@test.com") == null) {
            Utilisateur generaliste = new Utilisateur();
            generaliste.setNom("Bennani");
            generaliste.setPrenom("Youssef");
            generaliste.setEmail("generaliste@test.com");
            generaliste.setMotDePasse(PasswordUtil.hasher("1234"));
            generaliste.setRole(Role.GENERALISTE);
            utilisateurDAO.save(generaliste);
        }

        // 3. Spécialiste (cardiologue)
        if (utilisateurDAO.findByEmail("specialiste@test.com") == null) {
            Specialiste specialiste = new Specialiste();
            specialiste.setNom("Idrissi");
            specialiste.setPrenom("Karim");
            specialiste.setEmail("specialiste@test.com");
            specialiste.setMotDePasse(PasswordUtil.hasher("1234"));
            specialiste.setRole(Role.SPECIALISTE);
            specialiste.setSpecialite(Specialite.CARDIOLOGUE);
            specialiste.setTarif(300.0);
            specialisteDAO.save(specialiste);
        }

        System.out.println("Utilisateurs de test crees !");
        JPAUtil.close();
    }
}