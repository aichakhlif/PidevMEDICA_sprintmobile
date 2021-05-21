/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author admin
 */
public class rendez_vous {
      private int id ;
     private medecin med ;
     private String date;
     private String heure;
     private int etat;

    public rendez_vous() {
    }

    public rendez_vous(int id, medecin med, String date, String heure, int etat) {
        this.id = id;
        this.med = med;
        this.date = date;
        this.heure = heure;
        this.etat = etat;
    }

    

    
    public int getId() {
        return id;
    }

    public medecin getMed() {
        return med;
    }
    
    

    public String getDate() {
        return date;
    }

   

    public String getHeure() {
        return heure;
    }

    public int getEtat() {
        return etat;
    }

    
    
     public void setId(int id) {
        this.id =id;
    }

    public void setMed(medecin med) {
        this.med = med;
    }

     
     
    
    public void setDate(String date) {
        this.date = date;
    }

    

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    @Override
    public String toString() {
        return "Rendez-vous{" + "id=" + id + ", med=" + med +  '}';
    }

  
    
    
    
}
