package com.telexpertise.servlet;

import com.telexpertise.model.Utilisateur;
import com.telexpertise.service.ConsultationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/consultations")
public class ListeConsultationsServlet extends HttpServlet {

    private ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur generaliste = (Utilisateur) request.getSession().getAttribute("utilisateur");
        request.setAttribute("liste", consultationService.consultationsOuvertes(generaliste));
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/consultations.jsp")
                .forward(request, response);
    }
}