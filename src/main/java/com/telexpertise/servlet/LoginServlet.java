package com.telexpertise.servlet;

import com.telexpertise.dao.UtilisateurDAO;
import com.telexpertise.model.Utilisateur;
import com.telexpertise.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    // Affiche la page de login
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    // Traite le formulaire
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // 1. Chercher l'utilisateur par email
        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);

        // 2. Vérifier l'utilisateur et le mot de passe
        if (utilisateur != null && PasswordUtil.verifier(motDePasse, utilisateur.getMotDePasse())) {

            // 3. Créer la session et y mettre l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateur);

            // 4. Aller vers la page d'accueil (on la fera plus tard)
            response.sendRedirect(request.getContextPath() + "/accueil");

        } else {
            // Mauvais email ou mot de passe : on réaffiche le login avec un message
            request.setAttribute("erreur", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}