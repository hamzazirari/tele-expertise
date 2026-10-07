package com.telexpertise.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Transforme un mot de passe en texte haché (pour le stocker dans la base)
    public static String hasher(String motDePasse) {
        return BCrypt.hashpw(motDePasse, BCrypt.gensalt());
    }

    // Vérifie si le mot de passe tapé correspond au mot de passe haché de la base
    public static boolean verifier(String motDePasse, String motDePasseHache) {
        return BCrypt.checkpw(motDePasse, motDePasseHache);
    }
}