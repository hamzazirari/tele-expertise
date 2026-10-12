package com.telexpertise.servlet;

import com.telexpertise.model.Utilisateur;
import com.telexpertise.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String motDePasse = request.getParameter("motDePasse");

        // La vérification est faite par le service
        Utilisateur utilisateur = authService.authentifier(email, motDePasse);

        if (utilisateur != null) {
            // Sécurité : on détruit l'ancienne session avant d'en créer une nouvelle
            HttpSession ancienne = request.getSession(false);
            if (ancienne != null) {
                ancienne.invalidate();
            }

            // La session reste le travail de la servlet (c'est du web)
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateur);
            response.sendRedirect(request.getContextPath() + "/accueil");
        } else {
            request.setAttribute("erreur", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}