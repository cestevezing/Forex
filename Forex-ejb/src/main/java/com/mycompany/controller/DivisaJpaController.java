/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.entidades.Divisa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author HTTP
 */
public class DivisaJpaController implements Serializable {

    private EntityManager em = null;
    
    public DivisaJpaController() {
        
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    
    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Divisa divisa){
        try {
            em.getTransaction().begin();
            em.persist(divisa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void edit(Divisa divisa) {
      
        try {
            em.getTransaction().begin();
            Divisa div = em.find(Divisa.class, divisa.getId());

            div.setName(divisa.getName());
            div.setValue(divisa.getValue());

            em.getTransaction().commit();
      
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            em.close();
        }
      
    }

    public void destroy(Integer id){
        try {
            em.getTransaction().begin();
            Divisa div = em.find(Divisa.class, id);
            em.remove(div);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            em.close();
        }
      
    }

    public List<Divisa> findDivisaEntities() {
        return findDivisaEntities(true, -1, -1);
    }

    public List<Divisa> findDivisaEntities(int maxResults, int firstResult) {
        return findDivisaEntities(false, maxResults, firstResult);
    }

    private List<Divisa> findDivisaEntities(boolean all, int maxResults, int firstResult) {
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Divisa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Divisa findDivisa(Integer id) {
        try {
            return em.find(Divisa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDivisaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Divisa> rt = cq.from(Divisa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
