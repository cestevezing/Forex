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
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findById", query = "SELECT h FROM Historial h WHERE h.id = :id"),
    @NamedQuery(name = "Historial.findByValor", query = "SELECT h FROM Historial h WHERE h.valor = :valor")})


public class Historial implements Serializable {
    
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
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "divisa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Divisa divisaId;
    /*
    Constructor vacio
    */
    public Historial() {
    }
    /**
     * 
     * @param id 
     */
    public Historial(Integer id) {
        this.id = id;
    }
    /**
     * 
     * @param id
     * @param valor 
     */
    public Historial(Integer id, double valor) {
        this.id = id;
        this.valor = valor;
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
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
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
        return "com.mycompany.entidades.Historial[ id=" + id + " ]";
    }
    
}
