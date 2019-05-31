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
public class DivisaP {

    private int id;
    private String name;
    private double value;
    
    public DivisaP() {
    }
/**
 * 
 * @param id
 * @param name
 * @param value 
 */
    public DivisaP(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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
    public double getValue() {
        return value;
    }
    /**
     * 
     * @param value 
     */
    public void setValue(double value) {
        this.value = value;
    }
    
    
}
