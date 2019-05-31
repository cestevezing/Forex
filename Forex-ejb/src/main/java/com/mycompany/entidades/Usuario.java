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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByName", query = "SELECT u FROM Usuario u WHERE u.name = :name"),
    @NamedQuery(name = "Usuario.findByNameUser", query = "SELECT u FROM Usuario u WHERE u.nameUser = :nameUser"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByOutlay", query = "SELECT u FROM Usuario u WHERE u.outlay = :outlay"),
    @NamedQuery(name = "Usuario.findByEarnings", query = "SELECT u FROM Usuario u WHERE u.earnings = :earnings")})


public class Usuario implements Serializable {
    
    /*
    Declaracion de variables y objetos con sus respectivas anotaciones
    */
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name_user")
    private String nameUser;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "outlay")
    private Double outlay;
    @Column(name = "earnings")
    private Double earnings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Transaccion> transaccionList;
    /*
    Constructor vacio
    */
    public Usuario() {
    }
    /**
     * 
     * @param id 
     */
    public Usuario(Integer id) {
        this.id = id;
    }
    /**
     * 
     * @param id
     * @param name
     * @param nameUser
     * @param email
     * @param password 
     */
    public Usuario(Integer id, String name, String nameUser, String email, String password) {
        this.id = id;
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
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
    public Double getOutlay() {
        return outlay;
    }
    /**
     * 
     * @param outlay 
     */
    public void setOutlay(Double outlay) {
        this.outlay = outlay;
    }
    /**
     * 
     * @return 
     */
    public Double getEarnings() {
        return earnings;
    }
    /**
     * 
     * @param earnings 
     */
    public void setEarnings(Double earnings) {
        this.earnings = earnings;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
        return "com.mycompany.entidades.Usuario[ id=" + id + " ]";
    }
    
}
