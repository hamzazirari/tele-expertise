package com.telexpertise.servlet;

import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.dao.PatientDAO;
import com.telexpertise.dao.SignesVitauxDAO;
import com.telexpertise.model.FileAttente;
import com.telexpertise.model.Patient;
import com.telexpertise.model.SignesVitaux;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/infirmier/nouveau-patient")
public class NouveauPatientServlet extends HttpServlet {

    private PatientDAO patientDAO = new PatientDAO();
    private SignesVitauxDAO signesVitauxDAO = new SignesVitauxDAO();
    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();

    // Affiche le formulaire (le numéro de sécu est pré-rempli s'il vient de la recherche)
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

        // Sécurité : si le numéro existe déjà, on ne crée pas de doublon
        if (patientDAO.findByNumeroSecuriteSociale(numeroSecu) != null) {
            request.setAttribute("erreur", "Un patient avec ce numéro existe déjà");
            request.setAttribute("numeroSecu", numeroSecu);
            request.getRequestDispatcher("/WEB-INF/jsp/infirmier/nouveau-patient.jsp")
                    .forward(request, response);
            return;
        }

        // 1. Créer le patient
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
        patientDAO.save(patient);

        // 2. Créer les signes vitaux
        SignesVitaux signes = new SignesVitaux();
        signes.setTensionArterielle(request.getParameter("tension"));
        signes.setFrequenceCardiaque(Integer.parseInt(request.getParameter("frequenceCardiaque")));
        signes.setTemperature(Double.parseDouble(request.getParameter("temperature")));
        signes.setFrequenceRespiratoire(Integer.parseInt(request.getParameter("frequenceRespiratoire")));
        signes.setPoids(lireDouble(request.getParameter("poids")));
        signes.setTaille(lireDouble(request.getParameter("taille")));
        signes.setDateMesure(LocalDateTime.now());
        signes.setPatient(patient);
        signesVitauxDAO.save(signes);

        // 3. Ajouter à la file d'attente
        FileAttente file = new FileAttente();
        file.setPatient(patient);
        file.setSignesVitaux(signes);
        file.setHeureArrivee(LocalDateTime.now());
        fileAttenteDAO.save(file);

        response.sendRedirect(request.getContextPath() + "/infirmier/accueil-patient");
    }

    // Poids et taille sont "si nécessaire" : le champ peut être vide
    private Double lireDouble(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            return null;
        }
        return Double.parseDouble(valeur);
    }
}