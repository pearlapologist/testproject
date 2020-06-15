/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import models.*;

/**
 *
 * @author bayan
 */

public class TestUpload extends HttpServlet {

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
         getServletContext().getRequestDispatcher("/WEB-INF/test/testUpload.jsp")
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
        
        String path = "";
        try{
       path = "C:\\Users\\bayan\\OneDrive\\Документы\\NetBeansProjects\\Test\\web\\WEB-INF\\Content";
        }catch(Exception e){}
        Part filePart = request.getPart("file");
        String fileName = DataUtils.generateRandomString(15)+".jpg";
        
        OutputStream out = null;
        InputStream fileContent = null;
        final PrintWriter writer = response.getWriter();
        
        try{
        out  = new FileOutputStream(new File(path + File.separator + fileName));
        fileContent = filePart.getInputStream();
        
        int read = 0;
        final byte [] bytes = new byte[1024];
        
        while((read = fileContent.read(bytes)) != -1){
        out.write(bytes, 0, read);
        }
        writer.println("New file " + fileName + " created in " + path);
        
        
        }catch(FileNotFoundException e){
           writer.println("error");
              writer.println("</br>" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
        
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
