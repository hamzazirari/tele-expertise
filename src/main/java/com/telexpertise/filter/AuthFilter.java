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

        String chemin = request.getRequestURI().substring(request.getContextPath().length());

        if (chemin.equals("/login") || chemin.startsWith("/css/")
                || chemin.startsWith("/js/") || chemin.startsWith("/images/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        Utilisateur utilisateur = null;
        if (session != null) {
            utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        }

        if (utilisateur == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

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

        chain.doFilter(request, response);
    }
}