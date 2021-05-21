/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.zied;

/**
 *
 * @author admin
 */
public class medecin {
         private int idmed;
              private String nommed;

    public medecin() {
    }

    public medecin(int idmed, String nommed) {
        this.idmed = idmed;
        this.nommed = nommed;
    }

  
    public medecin(String nommed) {
          this.nommed = nommed;
    }

    public int getIdmed() {
        return idmed;
    }

    public String getNommed() {
        return nommed;
    }

    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    public void setNommed(String nommed) {
        this.nommed = nommed;
    }

    @Override
    public String toString() {
        return "medecin{" + "idmed=" + idmed + ", nommed=" + nommed + '}';
    }
              
              
              
}
