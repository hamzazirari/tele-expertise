package com.telexpertise.servlet;

import com.telexpertise.dao.ConsultationDAO;
import com.telexpertise.enums.StatutConsultation;
import com.telexpertise.model.Consultation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generaliste/detail-consultation")
public class DetailConsultationServlet extends HttpServlet {

    private ConsultationDAO consultationDAO = new ConsultationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Consultation consultation = consultationDAO.findById(id);

        request.setAttribute("consultation", consultation);
        request.getRequestDispatcher("/WEB-INF/jsp/generaliste/detail-consultation.jsp")
                .forward(request, response);
    }

    // Scénario A : diagnostic + traitement + clôture
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Consultation consultation = consultationDAO.findById(id);

        consultation.setDiagnostic(request.getParameter("diagnostic"));
        consultation.setTraitement(request.getParameter("traitement"));
        consultation.setStatut(StatutConsultation.TERMINEE);
        consultationDAO.update(consultation);

        response.sendRedirect(request.getContextPath() + "/generaliste/consultations");
    }
}