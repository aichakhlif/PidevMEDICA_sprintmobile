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
public class intervention {
          private int idinter;
              private String type;

    public intervention() {
    }

    public intervention(int idinter, String type) {
        this.idinter = idinter;
        this.type = type;
    }

    public intervention(String type) {
        this.type = type;
    }

    public int getIdinter() {
        return idinter;
    }

    public String getType() {
        return type;
    }

    public void setIdinter(int idinter) {
        this.idinter = idinter;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "intervention{" + "idinter=" + idinter + ", type=" + type + '}';
    }
              
              
}
