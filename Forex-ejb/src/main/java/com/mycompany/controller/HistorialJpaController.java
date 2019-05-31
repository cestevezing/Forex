/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entidades.Historial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author Valeria y Cristian
 */
public class HistorialJpaController implements Serializable {
    
    /*
    Constructor de la clase que controla el Historial con JPA
    */
    
    public HistorialJpaController() {
        this.utx = utx;
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx = null;
    public EntityManager em = null;
    
    /*
    Metodo que retorna Entidad
    */
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    /*
    Metodo para crear
    */
    
    public void create(Historial historial){
        try {
            em.getTransaction().begin();
            em.persist(historial);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //em.close();
        }
    }
    /*
    Metodo para editar
    */
    public void edit(Historial historial){
        try {
            em.getTransaction().begin();
            Historial his = em.find(Historial.class, historial.getId());
            his.setDivisaId(historial.getDivisaId());
            his.setValor(historial.getValor());
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {

            //em.close();

        }
    }

    public void destroy(Integer id){
        
    }

    public List<Historial> findHistorialEntities() {
        return findHistorialEntities(true, -1, -1);
    }

    public List<Historial> findHistorialEntities(int maxResults, int firstResult) {
        return findHistorialEntities(false, maxResults, firstResult);
    }
    /*
    Metodo que retorna entidades 
    */
    private List<Historial> findHistorialEntities(boolean all, int maxResults, int firstResult) {
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historial.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            //em.close();
        }
    }

    public Historial findHistorial(Integer id) {
        try {
            return em.find(Historial.class, id);
        } finally {
            //em.close();
        }
    }
    /*
    metodo que obtiene query del historial
    */
    public int getHistorialCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historial> rt = cq.from(Historial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            //em.close();
        }
    }
    
}
