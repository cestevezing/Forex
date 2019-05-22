/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author HTTP
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

    public TransaccionP(int id, int userId, int divisaId, double base, double actual, boolean state, double valuePip) {
        this.id = id;
        this.userId = userId;
        this.divisaId = divisaId;
        this.base = base;
        this.actual = actual;
        this.state = state;
        this.valuePip = valuePip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDivisaId() {
        return divisaId;
    }

    public void setDivisaId(int divisaId) {
        this.divisaId = divisaId;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public double getValuePip() {
        return valuePip;
    }

    public void setValuePip(double valuePip) {
        this.valuePip = valuePip;
    }
    
    
    
    
}
