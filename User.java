/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;

/**
 *
 * @author hp
 */
public class User {
     private int id,age;
    private String genre,password,email,pays,name,reset_token,activation_token;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", age=" + age + ", genre=" + genre + ", password=" + password + ", email=" + email + ", pays=" + pays + ", name=" + name + ", reset_token=" + reset_token + ", activation_token=" + activation_token + '}';
    }

    public User(int id, int age, String genre, String password, String email, String pays, String name, String reset_token, String activation_token) {
        this.id = id;
        this.age = age;
        this.genre = genre;
        this.password = password;
        this.email = email;
        this.pays = pays;
        this.name = name;
        this.reset_token = reset_token;
        this.activation_token = activation_token;
    }


   
    public User() {
    }

   

    public User(int age, String genre, String password, String email, String pays, String name) {
        this.age = age;
        this.genre = genre;
        this.password = password;
        this.email = email;
        this.pays = pays;
        this.name = name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

   

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getGenre() {
        return genre;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPays() {
        return pays;
    }

    public String getName() {
        return name;
    }

    public String getReset_token() {
        return reset_token;
    }

    public String getActivation_token() {
        return activation_token;
    }

  
    
}
