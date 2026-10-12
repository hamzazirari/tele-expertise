package com.telexpertise.servlet;

import com.telexpertise.service.ConsultationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/detail-consultation")
public class DetailConsultationServlet extends HttpServlet {

    private ConsultationService consultationService = new ConsultationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("consultation", consultationService.trouverParId(id));
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/detail-consultation.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        consultationService.cloturer(id,
                request.getParameter("diagnostic"), request.getParameter("traitement"));
        response.sendRedirect(request.getContextPath() + "/generaliste/consultations");
    }
}