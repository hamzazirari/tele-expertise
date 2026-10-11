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
import java.util.List;

@WebServlet("/infirmier/liste-patients")
public class ListePatientsServlet extends HttpServlet {

    private FileAttenteDAO fileAttenteDAO = new FileAttenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Les patients enregistrés aujourd'hui, triés par heure d'arrivée
        List<FileAttente> liste = fileAttenteDAO.findByDate(LocalDate.now());

        request.setAttribute("liste", liste);
        request.getRequestDispatcher("/WEB-INF/jsp/infirmier/liste-patients.jsp")
                .forward(request, response);
    }
}