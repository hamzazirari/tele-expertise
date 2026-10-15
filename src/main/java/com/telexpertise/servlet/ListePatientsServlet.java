package com.telexpertise.servlet;

import com.telexpertise.dao.FileAttenteDAO;
import com.telexpertise.model.FileAttente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import com.telexpertise.service.FileAttenteService;
import java.util.List;

@WebServlet("/infirmier/liste-patients")
public class ListePatientsServlet extends HttpServlet {

    private FileAttenteService fileAttenteService = new FileAttenteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<FileAttente> liste = fileAttenteService.patientsDuJour();

        request.setAttribute("liste", liste);
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/liste-patients.jsp")
                .forward(request, response);
    }
}