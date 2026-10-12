package com.telexpertise.servlet;

import com.telexpertise.enums.Specialite;
import com.telexpertise.service.ConsultationService;
import com.telexpertise.service.SpecialisteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/choix-specialiste")
public class ChoixSpecialisteServlet extends HttpServlet {

    private ConsultationService consultationService = new ConsultationService();
    private SpecialisteService specialisteService = new SpecialisteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        String choix = request.getParameter("specialite");
        if (choix != null && !choix.isEmpty()) {
            Specialite specialite = Specialite.valueOf(choix);
            request.setAttribute("liste", specialisteService.parSpecialite(specialite));
            request.setAttribute("specialiteChoisie", choix);
        }

        request.setAttribute("consultation", consultationService.trouverParId(id));
        request.setAttribute("specialites", Specialite.values());
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/choix-specialiste.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        consultationService.demanderAvisSpecialiste(id);
        response.sendRedirect(request.getContextPath() + "/generaliste/choix-specialiste?id=" + id);
    }
}