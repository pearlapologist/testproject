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
public class Signup extends HttpServlet {
    private static final String SIGNUP_JSP = "/WEB-INF/auth/signup.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

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
      getServletContext().getRequestDispatcher(SIGNUP_JSP)
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
     String name = request.getParameter("personName");
     String lastName = request.getParameter("personLast");
       
     String passwd = request.getParameter("passwd");
     String confirm = request.getParameter("confirm");
     
    
     String nm = request.getParameter("number");
     String numb = "87000000000";
     if(nm != null)
     {     numb = nm;
     }
          
     if(!(passwd.equals(confirm))){
       request.setAttribute("pswdNotEq", "Password mismatch");
          getServletContext().getRequestDispatcher(SIGNUP_JSP)
        .forward(request, response);
          return;
         }
     
     DbHelper db = new DbHelper();
     Person p = db.getPersonByNumb(numb);
     if(p!=null){
     request.setAttribute("sameNumb", "Пользователь с таким номером уже зарегистрирован");
        getServletContext().getRequestDispatcher(SIGNUP_JSP)
        .forward(request, response);
        return;
     }
     
     
         Person person = new Person(name, lastName,passwd, numb, 0);
        db.addPerson(person);
         response.sendRedirect("Login");
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
