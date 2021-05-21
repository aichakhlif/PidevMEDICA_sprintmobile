/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiesyassin;

/**
 *
 * @author Asus
 */
public class clinique {
     private int id;
    private String adresse;
    private int tel;
    private String nom, email;
    private String img;
  private String specialite;

    public clinique(int id, String adresse, int tel, String nom,  String img, String specialite,String email) {
        
        this.id = id;
        this.adresse = adresse;
        this.tel = tel;
        this.nom = nom;
      this.img = img;
        this.specialite = specialite;
        this.email = email;
    
    }

    public clinique() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    
  
}
