package com.telexpertise.servlet;

import com.telexpertise.dao.ConsultationDAO;
import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.enums.StatutConsultation;
import com.telexpertise.model.Consultation;
import com.telexpertise.model.FileAttente;
import com.telexpertise.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/generaliste/nouvelle-consultation")
public class NouvelleConsultationServlet extends HttpServlet {

    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();
    private ConsultationDAO consultationDAO = new ConsultationDAO();

    // Affiche le dossier du patient + le formulaire
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long fileId = Long.parseLong(request.getParameter("fileId"));
        FileAttente file = fileAttenteDAO.findById(fileId);

        request.setAttribute("file", file);
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/nouvelle-consultation.jsp")
                .forward(request, response);
    }

    // Crée la consultation et retire le patient de la file
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long fileId = Long.parseLong(request.getParameter("fileId"));
        FileAttente file = fileAttenteDAO.findById(fileId);

        Utilisateur generaliste = (Utilisateur) request.getSession().getAttribute("utilisateur");

        Consultation consultation = new Consultation();
        consultation.setPatient(file.getPatient());
        consultation.setGeneraliste(generaliste);
        consultation.setMotif(request.getParameter("motif"));
        consultation.setObservations(request.getParameter("observations"));
        consultation.setDateConsultation(LocalDateTime.now());
        consultation.setCout(150.0);
        consultation.setStatut(StatutConsultation.EN_COURS);
        consultationDAO.save(consultation);

        // Le patient quitte la file d'attente
        fileAttenteDAO.delete(file);

        response.sendRedirect(request.getContextPath() + "/generaliste/file-attente");
    }
}