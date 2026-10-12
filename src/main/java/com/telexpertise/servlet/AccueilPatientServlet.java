package com.telexpertise.servlet;

import com.telexpertise.model.Patient;
import com.telexpertise.service.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infirmier/accueil-patient")
public class AccueilPatientServlet extends HttpServlet {

    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/recherche-patient.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numero = request.getParameter("numeroSecu");
        Patient patient = patientService.rechercher(numero);

        request.setAttribute("numeroSecu", numero);
        request.setAttribute("trouve", patient != null);
        request.setAttribute("patient", patient);

        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/recherche-patient.jsp")
                .forward(request, response);
    }
}