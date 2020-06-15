/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import models.*;

/**
 *
 * @author bayan
 */
@MultipartConfig
public class EditProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /* protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
   
           
        }
    } */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Person person = models.Account.getCurrentPerson(request);

        if (person == null) {
            response.sendRedirect("Login");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/controllers/editProfile.jsp")
                .forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Person person = models.Account.getCurrentPerson(request);

        if (person == null) {
            response.sendRedirect("Login");
            return;
        }

        String path = "C:\\Users\\bayan\\OneDrive\\Документы\\NetBeansProjects\\Test\\web\\WEB-INF\\Content"; //getServletContext().getRealPath("/Content");

        Part filePart = request.getPart("file");
        String fileName = DataUtils.generateRandomString(15) + ".jpg";

        try {
            DataUtils.savePhoto(filePart, path, fileName);
            person.setPhoto(fileName);

        } catch (Exception e) {
        }

        DbHelper db = new DbHelper();
        db.updatePerson(person);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
