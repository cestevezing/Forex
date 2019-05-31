/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author Valeria y Cristian
 */
public class UsuarioP {

    private int id;
    private String name;
    private String nameUser;
    private String email;
    private String password;
    private double outlay;
    private double earnings;
    
    public UsuarioP() {
    }
    /**
     * 
     * @param id
     * @param name
     * @param nameUser
     * @param email
     * @param password
     * @param outlay
     * @param earnings 
     */
    public UsuarioP(int id, String name, String nameUser, String email, String password, int outlay, int earnings) {
        this.id = id;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.outlay = outlay;
        this.earnings = earnings;
    }
    
    
    /**
     * 
     * @return 
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 
     * @return 
     */
    public int getId() {
        return id;
    }
    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @return 
     */
    public String getNameUser() {
        return nameUser;
    }
    /**
     * 
     * @param nameUser 
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    /**
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }
    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 
     * @return 
     */
    public double getOutlay() {
        return outlay;
    }
    /**
     * 
     * @param outlay 
     */
    public void setOutlay(double outlay) {
        this.outlay = outlay;
    }
    /**
     * 
     * @return 
     */
    public double getEarnings() {
        return earnings;
    }
    /**
     * 
     * @param earnings 
     */
    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
    
    
    
}
