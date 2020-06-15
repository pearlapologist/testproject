/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;

/**
 *
 * @author bayan
 */
public class Mavens extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Person person = Account.getCurrentPerson(request);
        if (person == null) {
            response.sendRedirect("Login");
            return;
        }
         DbHelper db = new DbHelper();

        int page = 1, size = 15;
        
        try{
        page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){ }
        
           
        if(page<=0){
        page =1;
        }
       
        int listSize = db.getExecutorsCount();
        
         int lastPage = listSize/size;
        if(listSize>lastPage*size){
        lastPage++;
        }
             
        if(page>lastPage){
        page = lastPage;
        }
        
        
        ArrayList<Executor> executors = db.getExecutorsRecords(page, size);
        
        
        
      request.setAttribute("executorsAll", executors);
           request.setAttribute("executorsCurPage", page);
              request.setAttribute("executorsLastPage", lastPage);
  
        getServletContext().getRequestDispatcher("/WEB-INF/controllers/mavens.jsp")
                .forward(request, response);

    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
