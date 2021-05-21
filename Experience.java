/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;



/**
 *
 * @author bhk
 */
public class Experience {
    private int id,note,id_client,points;
    private String titre,description,image,endroit;
  

    public Experience(int id, int note, int id_client, int points, String titre, String description, String image, String endroit) {
        this.id = id;
        this.note = note;
        this.id_client = id_client;
        this.points = points;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.endroit = endroit;
       
    }
public Experience( String titre, String description, String endroit,int note) {
       
        this.note = note;
        
     
        this.titre = titre;
        this.description = description;
  
        this.endroit = endroit;
        
    }
    
    public Experience() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEndroit() {
        return endroit;
    }

    public void setEndroit(String endroit) {
        this.endroit = endroit;
    }

    @Override
    public String toString() {
        return "Experience{" + "titre=" + titre + ", description=" + description + ", endroit=" + endroit + '}';
    }

   
    
}
