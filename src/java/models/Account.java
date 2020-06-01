
package models;

import javax.servlet.http.*;

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
