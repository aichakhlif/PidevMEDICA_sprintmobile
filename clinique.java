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
public class clinique {
     private int idclinique;
              private String nomclinique;

    public clinique() {
    }

    public clinique(int idclinique, String nomclinique) {
        this.idclinique = idclinique;
        this.nomclinique = nomclinique;
    }

    public clinique(String nomclinique) {
        this.nomclinique = nomclinique;
    }

    
    public int getIdclinique() {
        return idclinique;
    }

    public String getNomclinique() {
        return nomclinique;
    }

    public void setIdclinique(int idclinique) {
        this.idclinique = idclinique;
    }

    public void setNomclinique(String nomclinique) {
        this.nomclinique = nomclinique;
    }

    @Override
    public String toString() {
        return "clinique{" + "idclinique=" + idclinique + ", nomclinique=" + nomclinique + '}';
    }

}
