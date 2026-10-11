package com.telexpertise.servlet;

import com.telexpertise.dao.ConsultationDAO;
import com.telexpertise.enums.StatutConsultation;
import com.telexpertise.model.Consultation;
import com.telexpertise.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/generaliste/consultations")
public class ListeConsultationsServlet extends HttpServlet {

    private ConsultationDAO consultationDAO = new ConsultationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utilisateur generaliste = (Utilisateur) request.getSession().getAttribute("utilisateur");

        // Consultations encore ouvertes (EN_COURS)
        List<Consultation> liste = consultationDAO
                .findByGeneralisteEtStatut(generaliste, StatutConsultation.EN_COURS);

        request.setAttribute("liste", liste);
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/consultations.jsp")
                .forward(request, response);
    }
}