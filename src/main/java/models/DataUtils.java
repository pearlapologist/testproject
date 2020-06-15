/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.*;

/**
 *
 * @author bayan
 */
public class DataUtils {

    public static Long convertDataToLong(String res) {
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static Long convertDataToLong(int day, int month, int year) {
        String res = day + "/" + month + "/" + year;
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static Long convertDataToLongWithRawString(String str) {
        String day = str.substring(0, 2);
        String month = str.substring(3, 5);
        String year = str.substring(6, 10);
        String res = day + "/" + month + "/" + year;
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(res);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    public static String convertLongToDataString(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(date));
        return dateString;
    }

    public static String getCurrentDateInString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String data = df.format(dateobj);
        return data;
    }

    public static Long getCurentDateInLong() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String data = df.format(dateobj);
        Long l = convertDataToLongWithRawString(data);
        return l;
    }

    public static String PATH = "/Test/";

    public static String generateRandomString(int length) {
        String source = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            int randSynb = rand.nextInt(source.length());
            result += source.charAt(randSynb);

        }
        return result;
    }
    
    public static void savePhoto(Part filePart, String path, String fileName) throws Exception{
        
        OutputStream out = null;
       InputStream fileContent = null;
        
        try{
        out  = new java.io.FileOutputStream(new File(path + File.separator + fileName));
        fileContent = filePart.getInputStream();
        
        int read = 0;
        final byte [] bytes = new byte[1024];
        
        while((read = fileContent.read(bytes)) != -1){
        out.write(bytes, 0, read);
        }
        
        }catch(FileNotFoundException e){
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
        
    }
    
    
      public static String getPersonPhotoPath(Person p) {
        if (p.getPhoto() == null || p.getPhoto().equals("")) {
            return "Content/executors_default_image.png";

        }
        else {
            return "Content/" + p.getPhoto();

        }
    }
}
