package com.telexpertise.servlet;

import com.telexpertise.model.Utilisateur;
import com.telexpertise.service.ConsultationService;
import com.telexpertise.service.FileAttenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/nouvelle-consultation")
public class NouvelleConsultationServlet extends HttpServlet {

    private FileAttenteService fileAttenteService = new FileAttenteService();
    private ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long fileId = Long.parseLong(request.getParameter("fileId"));
        request.setAttribute("file", fileAttenteService.trouverParId(fileId));
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/nouvelle-consultation.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long fileId = Long.parseLong(request.getParameter("fileId"));
        Utilisateur generaliste = (Utilisateur) request.getSession().getAttribute("utilisateur");

        consultationService.creerConsultation(fileId, generaliste,
                request.getParameter("motif"), request.getParameter("observations"));

        response.sendRedirect(request.getContextPath() + "/generaliste/file-attente");
    }
}