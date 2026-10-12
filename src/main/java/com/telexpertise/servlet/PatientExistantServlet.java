package com.telexpertise.servlet;

import com.telexpertise.model.Patient;
import com.telexpertise.model.SignesVitaux;
import com.telexpertise.service.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/infirmier/patient-existant")
public class PatientExistantServlet extends HttpServlet {

    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("patient", patientService.trouverParId(id));
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/patient-existant.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Patient patient = patientService.trouverParId(id);

        SignesVitaux signes = new SignesVitaux();
        signes.setTensionArterielle(request.getParameter("tension"));
        signes.setFrequenceCardiaque(Integer.parseInt(request.getParameter("frequenceCardiaque")));
        signes.setTemperature(Double.parseDouble(request.getParameter("temperature")));
        signes.setFrequenceRespiratoire(Integer.parseInt(request.getParameter("frequenceRespiratoire")));
        signes.setPoids(lireDouble(request.getParameter("poids")));
        signes.setTaille(lireDouble(request.getParameter("taille")));

        patientService.accueillir(patient, signes);

        response.sendRedirect(request.getContextPath() + "/infirmier/accueil-patient");
    }

    private Double lireDouble(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            return null;
        }
        return Double.parseDouble(valeur);
    }
}