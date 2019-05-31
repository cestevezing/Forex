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
public class HistorialP {
    private int id;
    private int divisaId;
    private double valor;
    
    public HistorialP(){
        
    }
    /**
     * 
     * @param id
     * @param divisaId
     * @param valor 
     */
    public HistorialP(int id, int divisaId, double valor) {
        this.id = id;
        this.divisaId = divisaId;
        this.valor = valor;
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
    public double getValor() {
        return valor;
    }
    /**
     * 
     * @param valor 
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
