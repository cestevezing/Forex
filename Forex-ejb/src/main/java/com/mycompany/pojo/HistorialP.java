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
public class HistorialP {
    private int id;
    private int divisaId;
    private double valor;
    
    public HistorialP(){
        
    }

    public HistorialP(int id, int divisaId, double valor) {
        this.id = id;
        this.divisaId = divisaId;
        this.valor = valor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getDivisaId() {
        return divisaId;
    }

    public void setDivisaId(int divisaId) {
        this.divisaId = divisaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
