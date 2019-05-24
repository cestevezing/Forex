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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HTTP
 */
@Entity
@Table(name = "seguridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguridad.findAll", query = "SELECT s FROM Seguridad s"),
    @NamedQuery(name = "Seguridad.findById", query = "SELECT s FROM Seguridad s WHERE s.id = :id"),
    @NamedQuery(name = "Seguridad.findByToken", query = "SELECT s FROM Seguridad s WHERE s.token = :token"),
    @NamedQuery(name = "Seguridad.findByUser", query = "SELECT s FROM Seguridad s WHERE s.user = :user")})
public class Seguridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 400)
    @Column(name = "token")
    private String token;
    @Column(name = "user")
    private Integer user;

    public Seguridad() {
    }

    public Seguridad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguridad)) {
            return false;
        }
        Seguridad other = (Seguridad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entidades.Seguridad[ id=" + id + " ]";
    }
    
}
