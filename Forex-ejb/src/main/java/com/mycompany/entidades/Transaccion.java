/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valeria y Cristian
 */
@Entity
@Table(name = "transaccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
    @NamedQuery(name = "Transaccion.findById", query = "SELECT t FROM Transaccion t WHERE t.id = :id"),
    @NamedQuery(name = "Transaccion.findByBase", query = "SELECT t FROM Transaccion t WHERE t.base = :base"),
    @NamedQuery(name = "Transaccion.findByActual", query = "SELECT t FROM Transaccion t WHERE t.actual = :actual"),
    @NamedQuery(name = "Transaccion.findByState", query = "SELECT t FROM Transaccion t WHERE t.state = :state"),
    @NamedQuery(name = "Transaccion.findByValuePip", query = "SELECT t FROM Transaccion t WHERE t.valuePip = :valuePip")})


public class Transaccion implements Serializable {
    
    /*
    Declaracion de variables y objetos con sus respectivas anotaciones
    */
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "base")
    private double base;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual")
    private double actual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state")
    private boolean state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value_pip")
    private double valuePip;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario userId;
    @JoinColumn(name = "divisa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Divisa divisaId;
    /*
    Constructor vacio
    */
    public Transaccion() {
    }
    /**
     * 
     * @param id 
     */
    public Transaccion(Integer id) {
        this.id = id;
    }
    /**
     * 
     * @param id
     * @param base
     * @param actual
     * @param state
     * @param valuePip 
     */
    public Transaccion(Integer id, double base, double actual, boolean state, double valuePip) {
        this.id = id;
        this.base = base;
        this.actual = actual;
        this.state = state;
        this.valuePip = valuePip;
    }
    /**
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }
    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
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
    public boolean getState() {
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
    /**
     * 
     * @return 
     */
    public Usuario getUserId() {
        return userId;
    }
    /**
     * 
     * @param userId 
     */
    public void setUserId(Usuario userId) {
        this.userId = userId;
    }
    /**
     * 
     * @return 
     */
    public Divisa getDivisaId() {
        return divisaId;
    }
    /**
     * 
     * @param divisaId 
     */
    public void setDivisaId(Divisa divisaId) {
        this.divisaId = divisaId;
    }
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "com.mycompany.entidades.Transaccion[ id=" + id + " ]";
    }
    
}
