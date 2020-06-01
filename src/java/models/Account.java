/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.servlet.http.*;

/**
 *
 * @author bayan
 */
public class Account {
    
    public static Person getCurrentPerson(HttpServletRequest request){
        
        
    HttpSession session = request.getSession();
       int id = -1;
       
      try{
          id = (int)session.getAttribute("personIdSession");
      }catch(Exception e){
      }
      
      DbHelper db = new DbHelper();
        
        Person person = db.getPerson(id);
        return person;
    }    
    
}
