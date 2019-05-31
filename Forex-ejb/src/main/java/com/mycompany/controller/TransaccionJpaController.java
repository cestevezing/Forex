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
import com.mycompany.entidades.Transaccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author Valeria y Cristian
 */
public class TransaccionJpaController implements Serializable {
    /*
    Constructor de la clase que controla Transaccion con JPA
    */
    public TransaccionJpaController() {
        this.utx = utx;
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx;
    public EntityManager em ;
    /*
    Metodo que crea
    */
    public void create(Transaccion transaccion) {
        try {
            em.getTransaction().begin();
            em.persist(transaccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //em.close();
        }
    }
    /*
    Metodo que edita
    */
    public void edit(Transaccion transaccion) {
        try {
            em.getTransaction().begin();
            Transaccion tra = em.find(Transaccion.class, transaccion.getId());
            tra.setActual(transaccion.getActual());
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }
    /*
    Metodo que elimina
    */
    public void destroy(Integer id) {
        try {
            em.getTransaction().begin();
            Transaccion tran = em.find(Transaccion.class, id);
            em.remove(tran);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if(em != null){
                //em.close();
            }
            
        }
    }

    public List<Transaccion> findTransaccionEntities() {
        return findTransaccionEntities(true, -1, -1);
    }

    public List<Transaccion> findTransaccionEntities(int maxResults, int firstResult) {
        return findTransaccionEntities(false, maxResults, firstResult);
    }
    /*
    Metodo que retorna
    */
    private List<Transaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transaccion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if(em != null){
                //em.close();
            }
        }
    }

    public Transaccion findTransaccion(Integer id) {        
        try {
            return em.find(Transaccion.class, id);
        } finally {
            if(em != null){
                //em.close();
            }
        }
    }
    /*
    Metodo que obtiene query
    */
    public int getTransaccionCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transaccion> rt = cq.from(Transaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if(em != null){
                //em.close();
            }
        }
    }

}
