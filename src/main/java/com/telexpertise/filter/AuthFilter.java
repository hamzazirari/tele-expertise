package com.telexpertise.filter;

import com.telexpertise.enums.Role;
import com.telexpertise.model.Utilisateur;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Le chemin demandé, sans le nom de l'application (ex : "/login")
        String chemin = request.getRequestURI().substring(request.getContextPath().length());

        // 1. Pages libres : login (et fichiers css/js/images s'il y en a)
        if (chemin.equals("/login") || chemin.startsWith("/css/")
                || chemin.startsWith("/js/") || chemin.startsWith("/images/")) {
            chain.doFilter(request, response);
            return;
        }

        // 2. Vérifier qu'il y a une session avec un utilisateur connecté
        HttpSession session = request.getSession(false);
        Utilisateur utilisateur = null;
        if (session != null) {
            utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        }

        if (utilisateur == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 3. Vérifier le rôle selon l'URL
        Role role = utilisateur.getRole();

        if (chemin.startsWith("/infirmier/") && role != Role.INFIRMIER) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (chemin.startsWith("/generaliste/") && role != Role.GENERALISTE) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (chemin.startsWith("/specialiste/") && role != Role.SPECIALISTE) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // 4. Tout est bon : on continue vers la servlet
        chain.doFilter(request, response);
    }
}