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
import java.time.LocalDate;

@WebServlet("/infirmier/nouveau-patient")
public class NouveauPatientServlet extends HttpServlet {

    private PatientService patientService = new PatientService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("numeroSecu", request.getParameter("numeroSecu"));
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/nouveau-patient.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeroSecu = request.getParameter("numeroSecu");

        if (patientService.existe(numeroSecu)) {
            request.setAttribute("erreur", "Un patient avec ce numéro existe déjà");
            request.setAttribute("numeroSecu", numeroSecu);
            request.getRequestDispatcher("/WEB-INF/jsp/infirmier/nouveau-patient.jsp")
                    .forward(request, response);
            return;
        }

        Patient patient = new Patient();
        patient.setNom(request.getParameter("nom"));
        patient.setPrenom(request.getParameter("prenom"));
        patient.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance")));
        patient.setNumeroSecuriteSociale(numeroSecu);
        patient.setTelephone(request.getParameter("telephone"));
        patient.setAdresse(request.getParameter("adresse"));
        patient.setMutuelle(request.getParameter("mutuelle"));
        patient.setAntecedents(request.getParameter("antecedents"));
        patient.setAllergies(request.getParameter("allergies"));
        patient.setTraitementsEnCours(request.getParameter("traitementsEnCours"));

        SignesVitaux signes = lireSignes(request);

        patientService.creerPatientEtAccueillir(patient, signes);

        response.sendRedirect(request.getContextPath() + "/infirmier/accueil-patient");
    }

    // Transforme les champs du formulaire en objet SignesVitaux
    private SignesVitaux lireSignes(HttpServletRequest request) {
        SignesVitaux signes = new SignesVitaux();
        signes.setTensionArterielle(request.getParameter("tension"));
        signes.setFrequenceCardiaque(Integer.parseInt(request.getParameter("frequenceCardiaque")));
        signes.setTemperature(Double.parseDouble(request.getParameter("temperature")));
        signes.setFrequenceRespiratoire(Integer.parseInt(request.getParameter("frequenceRespiratoire")));
        signes.setPoids(lireDouble(request.getParameter("poids")));
        signes.setTaille(lireDouble(request.getParameter("taille")));
        return signes;
    }

    private Double lireDouble(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            return null;
        }
        return Double.parseDouble(valeur);
    }
}