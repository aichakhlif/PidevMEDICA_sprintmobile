/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author hp
 */
public class InformationsSupplementaires {
      int id;
    int user_id,age;
    String name;
    String pays;
    String genre;
    String email;

    public InformationsSupplementaires() {
    }

    public InformationsSupplementaires(int id, int user_id, int age, String name, String pays, String genre, String email) {
        this.id = id;
        this.user_id = user_id;
        this.age = age;
        this.name = name;
        this.pays = pays;
        this.genre = genre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getPays() {
        return pays;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
