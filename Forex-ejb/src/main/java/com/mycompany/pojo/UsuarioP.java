/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.io.Serializable;

/**
 *
 * @author HTTP
 */
public class UsuarioP implements Serializable {

    private int id;
    private String name;
    private String nameUser;
    private String email;
    private String password;
    private double outlay;
    private double earnings;
    
    public UsuarioP() {
    }

    public UsuarioP(int id, String name, String nameUser, String email, String password, double outlay, double earnings) {
        this.id = id;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.outlay = outlay;
        this.earnings = earnings;
    }
    
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getOutlay() {
        return outlay;
    }

    public void setOutlay(double outlay) {
        this.outlay = outlay;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
    
    
    
}
