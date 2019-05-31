/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Valeria y Cristian
 */
@Entity
@Table(name = "divisa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Divisa.findAll", query = "SELECT d FROM Divisa d"),
    @NamedQuery(name = "Divisa.findById", query = "SELECT d FROM Divisa d WHERE d.id = :id"),
    @NamedQuery(name = "Divisa.findByName", query = "SELECT d FROM Divisa d WHERE d.name = :name"),
    @NamedQuery(name = "Divisa.findByValue", query = "SELECT d FROM Divisa d WHERE d.value = :value")})


public class Divisa implements Serializable {
    
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
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private double value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisaId")
    private List<Transaccion> transaccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divisaId")
    private List<Historial> historialList;

    /*
    Constructor vacio
    */
    public Divisa() {
    }
    /**
     * 
     * @param id 
     */
    public Divisa(Integer id) {
        this.id = id;
    }
    /**
     * 
     * @param id
     * @param name
     * @param value 
     */
    public Divisa(Integer id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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
    /**
     * 
     * @return 
     */
    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }
    /**
     * 
     * @param transaccionList 
     */
    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }
    /**
     * 
     * @return 
     */
    @XmlTransient
    public List<Historial> getHistorialList() {
        return historialList;
    }
    /**
     * 
     * @param historialList 
     */
    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
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
        if (!(object instanceof Divisa)) {
            return false;
        }
        Divisa other = (Divisa) object;
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
        return "com.mycompany.entidades.Divisa[ id=" + id + " ]";
    }
    
}
