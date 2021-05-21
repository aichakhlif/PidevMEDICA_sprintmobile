/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.io.Preferences;
/**
 *
 * @author hp
 */
public final class SessionManager {
    public static Preferences pref ;
    
    private static SessionManager instance;
   private static  int id;
   private static  int  age;
   private  static String genre;
   private  static String password;
   private  static String email;
   private  static String pays ;
   private  static String name;
    
    
    
    
      public static SessionManager StartSession() {
        if(instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static SessionManager getSession() {
        return instance;
    }
     

    public SessionManager() {
        
    }

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static void setAge(int age) {
           pref.set("age",age);
    }

    public static void setGenre(String genre) {
            pref.set("genre",genre);
    }

    public static void setPassword(String password) {
          pref.set("password",password);
    }

    public static void setEmail(String email) {
           pref.set("email",email);
    }

    public static void setPays(String pays) {
          pref.set("pays",pays);
    }

    public static void setName(String name) {
           pref.set("name",name);
    }

    public static int getId() {
        return pref.get("id",id);
    }

    public static int getAge() {
       return pref.get("age",age);
    }

    public static  String getGenre() {
         return pref.get("genre",genre);
    }

    public static String getPassword() {
          return pref.get("password",password);
    }

    public static String getEmail() {
          return pref.get("email",email);
    }

    public static String getPays() {
         return pref.get("pays",pays);
    }

    public static  String getName() {
         return pref.get("name",name);
    }
    
    
    
    
    
    
}
