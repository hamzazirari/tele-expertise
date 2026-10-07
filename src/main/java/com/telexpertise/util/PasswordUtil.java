package com.telexpertise.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Transforme "1234" en texte haché illisible
    public static String hasher(String motDePasse) {
        return BCrypt.hashpw(motDePasse, BCrypt.gensalt());
    }

    // Vérifie si le mot de passe saisi correspond au haché stocké en base
    public static boolean verifier(String motDePasse, String motDePasseHache) {
        return BCrypt.checkpw(motDePasse, motDePasseHache);
    }
}