/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author bayan
 */
public class Person {
    private int id;
    private String name;
    private String lastname;
    private String passwd;
       private Long createdDate;
    private int rating;
    private String status;
    private String photo;
    private String number;
    boolean hasAccount;

    public Person(int id,String name, String lastname, String passwd) {
        this.name = name;
        this.id = id;
        this.lastname = lastname;
        this.passwd = passwd;
         Long created = DataUtils.getCurentDateInLong();
         this.createdDate = created;
    }
    
       public Person(String name, String lastname, String passwd) {
        this.name = name;
        this.lastname = lastname;
        this.passwd = passwd;
         Long created = DataUtils.getCurentDateInLong();
         this.createdDate = created;
    }
    
  
    
      //with number
          public Person(String name, String lastname, String number, String passwd) {
        this.name = name;
        this.lastname = lastname;
        this.number = number;
        this.passwd = passwd;
         Long created = DataUtils.getCurentDateInLong();
         this.createdDate = created;
    }
    
    
//    
// public Person(String name, String passwd) {
//        this.name = name;
//        this.passwd = passwd;
//    }
// 
 
   public Person(int id, String name, String lastname, String passwd, String photo, String number, int rating) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.lastname = lastname;
        this.rating = rating;
        this.photo = photo;
        this.number = number;
         Long created = DataUtils.getCurentDateInLong();
         this.createdDate = created;
    }
   
   //wout photo
       public Person(String name, String lastname, String passwd, String number, int rating) {
        this.passwd = passwd;
        this.name = name;
        this.lastname = lastname;
        this.rating = rating;
        this.photo = photo;
        this.number = number;
         Long created = DataUtils.getCurentDateInLong();
        this.createdDate = created;
    }
       
          public Person(int id, String name, String lastname, String passwd, String number, int rating, Long created) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.lastname = lastname;
        this.rating = rating;
        this.photo = photo;
        this.number = number;
        this.createdDate = created;
    }
   
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Long getCreatedDate() {
        return createdDate;
    }

//    public String getCreatedDateinString() {
//        Long create = this.createdDate;
//      String str =  DataUtils.convertLongToDataString(create);
//        return str;
//    }


    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isHasAccount() {
        return hasAccount;
    }

    public void setHasAccount(boolean hasAccount) {
        this.hasAccount = hasAccount;
    }
    
}
