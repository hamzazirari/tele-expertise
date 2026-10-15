package com.telexpertise.service;

import com.telexpertise.dao.UtilisateurDAO;
import com.telexpertise.model.Utilisateur;
import com.telexpertise.util.PasswordUtil;

public class AuthService {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public Utilisateur authentifier(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);

        if (utilisateur != null && PasswordUtil.verifier(motDePasse, utilisateur.getMotDePasse())) {
            return utilisateur;
        }
        return null;
    }
}