package com.telexpertise.servlet;

import com.telexpertise.enums.Priorite;
import com.telexpertise.model.Specialiste;
import com.telexpertise.service.ConsultationService;
import com.telexpertise.service.CreneauService;
import com.telexpertise.service.ExpertiseService;
import com.telexpertise.service.SpecialisteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/demande-expertise")
public class DemandeExpertiseServlet extends HttpServlet {

    private ConsultationService consultationService = new ConsultationService();
    private SpecialisteService specialisteService = new SpecialisteService();
    private CreneauService creneauService = new CreneauService();
    private ExpertiseService expertiseService = new ExpertiseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long consultationId = Long.parseLong(request.getParameter("consultationId"));
        Long specialisteId = Long.parseLong(request.getParameter("specialisteId"));
        Specialiste specialiste = specialisteService.trouverParId(specialisteId);

        request.setAttribute("consultation", consultationService.trouverParId(consultationId));
        request.setAttribute("specialiste", specialiste);
        request.setAttribute("creneaux", creneauService.creneauxDuSpecialiste(specialiste));
        request.setAttribute("priorites", Priorite.values());
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/demande-expertise.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long consultationId = Long.parseLong(request.getParameter("consultationId"));
        Long specialisteId = Long.parseLong(request.getParameter("specialisteId"));
        Specialiste specialiste = specialisteService.trouverParId(specialisteId);

        String creneauParam = request.getParameter("creneauId");
        boolean ok = false;
        if (creneauParam != null) {
            ok = expertiseService.creerDemande(consultationId, specialiste,
                    Long.parseLong(creneauParam),
                    request.getParameter("question"),
                    request.getParameter("donneesAnalyses"),
                    Priorite.valueOf(request.getParameter("priorite")));
        }

        if (ok) {
            response.sendRedirect(request.getContextPath() + "/generaliste/consultations");
        } else {
            response.sendRedirect(request.getContextPath() + "/generaliste/demande-expertise?consultationId="
                    + consultationId + "&specialisteId=" + specialisteId + "&erreur=1");
        }
    }
}