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
public class TransaccionP {

    private int id;
    private int userId;
    private int divisaId;
    private double base;
    private double actual;
    private boolean state;
    private double valuePip;
    
    public TransaccionP() {
        
    }
    /**
     * 
     * @param id
     * @param userId
     * @param divisaId
     * @param base
     * @param actual
     * @param state
     * @param valuePip 
     */
    public TransaccionP(int id, int userId, int divisaId, double base, double actual, boolean state, double valuePip) {
        this.id = id;
        this.userId = userId;
        this.divisaId = divisaId;
        this.base = base;
        this.actual = actual;
        this.state = state;
        this.valuePip = valuePip;
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
    public int getUserId() {
        return userId;
    }
    /**
     * 
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**
     * 
     * @return 
     */
    public int getDivisaId() {
        return divisaId;
    }
    /**
     * 
     * @param divisaId 
     */
    public void setDivisaId(int divisaId) {
        this.divisaId = divisaId;
    }
    /**
     * 
     * @return 
     */
    public double getBase() {
        return base;
    }
    /**
     * 
     * @param base 
     */
    public void setBase(double base) {
        this.base = base;
    }
    /**
     * 
     * @return 
     */
    public double getActual() {
        return actual;
    }
    /**
     * 
     * @param actual 
     */
    public void setActual(double actual) {
        this.actual = actual;
    }
    /**
     * 
     * @return 
     */
    public boolean isState() {
        return state;
    }
    /**
     * 
     * @param state 
     */
    public void setState(boolean state) {
        this.state = state;
    }
    /**
     * 
     * @return 
     */
    public double getValuePip() {
        return valuePip;
    }
    /**
     * 
     * @param valuePip 
     */
    public void setValuePip(double valuePip) {
        this.valuePip = valuePip;
    }
    
    
    
    
}
