/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;

/**
 *
 * @author bayan
 */
public class Login extends HttpServlet {
    private static final String LOGIN_JSP = "/WEB-INF/auth/login.jsp";

 
  

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
        getServletContext().getRequestDispatcher(LOGIN_JSP)
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
        response.getWriter().print("POST");
        
        String numb = request.getParameter("loginNumber");
       
         String passwd = request.getParameter("loginPasswd");
         
         DbHelper db = new DbHelper();
         Person p = db.getPersonNP(numb, passwd);
         
         if(p == null){
             request.setAttribute("error", "Пользователь не найден. Проверьте корректность введенных данных ");
                  getServletContext().getRequestDispatcher(LOGIN_JSP)
                    .forward(request, response);
         }
         
         HttpSession session = request.getSession();
         session.setAttribute("personIdSession", p.getId());
         
         
         response.sendRedirect("MyCabinet");
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
