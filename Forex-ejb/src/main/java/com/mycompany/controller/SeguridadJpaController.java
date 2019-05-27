/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.controller.exceptions.RollbackFailureException;
import com.mycompany.entidades.Seguridad;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
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
        this.utx = utx;
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx = null;
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Seguridad seguridad) {
        try {
            em.getTransaction().begin();
            em.persist(seguridad);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Seguridad seguridad) {
        try {
            em.getTransaction().begin();
            Seguridad seg = em.find(Seguridad.class, seguridad.getId());

            seg.setToken(seguridad.getToken());
            seg.setUser(seguridad.getUser());

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        
        try {
            utx.begin();
            em = getEntityManager();
            Seguridad seguridad;
            try {
                seguridad = em.getReference(Seguridad.class, id);
                seguridad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seguridad with id " + id + " no longer exists.", enfe);
            }
            em.remove(seguridad);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Seguridad> findSeguridadEntities() {
        return findSeguridadEntities(true, -1, -1);
    }

    public List<Seguridad> findSeguridadEntities(int maxResults, int firstResult) {
        return findSeguridadEntities(false, maxResults, firstResult);
    }

    private List<Seguridad> findSeguridadEntities(boolean all, int maxResults, int firstResult) {
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
            if (em != null) {
                em.close();
            }
        }
    }

    public Seguridad findSeguridad(Integer id) {
        try {
            return em.find(Seguridad.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
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
            if (em != null) {
                em.close();
            }
        }
    }

}
