/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.questions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;

/**
 * Question/Add
 * @author bayan
 */
public class QuestionAdd extends HttpServlet {

    private static final String QUESTIONADD_JSP = "/WEB-INF/questionadd.jsp";

    

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
        getServletContext().getRequestDispatcher(QUESTIONADD_JSP)
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
        String title = request.getParameter("title");
        String opt1 = request.getParameter("opt1");
        String opt2 = request.getParameter("opt2");
        String opt3 = request.getParameter("opt3");

        String opt4 = request.getParameter("opt4");
        
       int correct = 9;
       
       try{
           correct = Integer.parseInt(request.getParameter("correct").toString());
       }catch(Exception e){}
       
       
        if (title != null && opt1 != null &&  opt2 != null && 
            opt3 != null &&   opt4 != null && correct !=9) {
           
        DbHelper db = new models.DbHelper();
        Question question = new Question(title, opt1,opt2, opt3,opt4,correct);
        db.addQuestion(question);
        
        PrintWriter out = response.getWriter();
        out.print(question.getId()+", "+  question.getTitle()+ ", " + question.getOpt1());
        
        }
        
        
        
        else {
            request.setAttribute("NotFilled", "Not all fields are filled");
            getServletContext().getRequestDispatcher(QUESTIONADD_JSP)
                    .forward(request, response);
            return;
        }

       // response.sendRedirect("Login");

    
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
