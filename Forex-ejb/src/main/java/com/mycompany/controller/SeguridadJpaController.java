/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.controller.exceptions.PreexistingEntityException;
import com.mycompany.controller.exceptions.RollbackFailureException;
import com.mycompany.entidades.Seguridad;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author HTTP
 */
public class SeguridadJpaController implements Serializable {

    public SeguridadJpaController() {
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();

    }
    
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Seguridad seguridad) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            em.getTransaction().begin();
            em.persist(seguridad);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void edit(Seguridad seguridad) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            em.getTransaction().begin();
            Seguridad seg = em.find(Seguridad.class, seguridad.getId());

            seg.setToken(seguridad.getToken());
            seg.setUser(seguridad.getUser());

            em.getTransaction().commit();
      
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            em.close();
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            em.getTransaction().begin();
            Seguridad seg = em.find(Seguridad.class, id);
            em.remove(seg);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            em.close();
        }
    }

    public List<Seguridad> findSeguridadEntities() {
        return findSeguridadEntities(true, -1, -1);
    }

    public List<Seguridad> findSeguridadEntities(int maxResults, int firstResult) {
        return findSeguridadEntities(false, maxResults, firstResult);
    }

    private List<Seguridad> findSeguridadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Seguridad.class));
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

    public Seguridad findSeguridad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Seguridad.class, id);
        } finally {
            em.close();
        }
    }

    public int getSeguridadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Seguridad> rt = cq.from(Seguridad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
