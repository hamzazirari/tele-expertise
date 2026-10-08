package com.telexpertise.servlet;

import com.telexpertise.dao.PatientDAO;
import com.telexpertise.model.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infirmier/accueil-patient")
public class AccueilPatientServlet extends HttpServlet {

    private PatientDAO patientDAO = new PatientDAO();

    // Affiche le formulaire de recherche
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/recherche-patient.jsp")
                .forward(request, response);
    }

    // Traite la recherche
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numero = request.getParameter("numeroSecu");
        Patient patient = patientDAO.findByNumeroSecuriteSociale(numero);

        if (patient != null) {
            // Patient trouvé : on le garde pour la page suivante
            request.setAttribute("patient", patient);
            request.setAttribute("numeroSecu", numero);
            request.setAttribute("trouve", true);
        } else {
            // Patient inconnu : on propose de créer un nouveau dossier
            request.setAttribute("numeroSecu", numero);
            request.setAttribute("trouve", false);
        }

        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/recherche-patient.jsp")
                .forward(request, response);
    }
}