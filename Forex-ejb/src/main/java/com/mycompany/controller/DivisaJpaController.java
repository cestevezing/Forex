/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.exceptions.IllegalOrphanException;
import com.mycompany.controller.exceptions.NonexistentEntityException;
import com.mycompany.controller.exceptions.RollbackFailureException;
import com.mycompany.entidades.Divisa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.entidades.Transaccion;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.entidades.Historial;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author Valeria y Cristian
 */
public class DivisaJpaController implements Serializable {
    
    /*
    Constructor de la clase que controla Divida desde JPA
    */
    
    public DivisaJpaController() {
        this.utx = utx;
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx;
    public EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }
    /*
    Metodo crear
    */
    public void create(Divisa divisa) {
        try {
            em.getTransaction().begin();
            em.persist(divisa);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            //em.close();
        }
    }
    /*
    Metodo editar
    */
    public void edit(Divisa divisa) {
        try {
            em.getTransaction().begin();
            Divisa divi = em.find(Divisa.class, divisa.getId());
            divi.setValue(divisa.getValue());
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {

            //em.close();

        }
    }
    /*
    Metodo que elimina
    */
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            utx.begin();
            em = getEntityManager();
            Divisa divisa;
            try {
                divisa = em.getReference(Divisa.class, id);
                divisa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The divisa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Transaccion> transaccionListOrphanCheck = divisa.getTransaccionList();
            for (Transaccion transaccionListOrphanCheckTransaccion : transaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Divisa (" + divisa + ") cannot be destroyed since the Transaccion " + transaccionListOrphanCheckTransaccion + " in its transaccionList field has a non-nullable divisaId field.");
            }
            List<Historial> historialListOrphanCheck = divisa.getHistorialList();
            for (Historial historialListOrphanCheckHistorial : historialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Divisa (" + divisa + ") cannot be destroyed since the Historial " + historialListOrphanCheckHistorial + " in its historialList field has a non-nullable divisaId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(divisa);
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
    /*
    metodo que retorna entidades 
    */
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
            if (em != null) {
                //em.close();
            }
        }
    }
    /*
    metodo que busca objeto divisa
    */
    public Divisa findDivisa(Integer id) {
        try {
            return em.find(Divisa.class, id);
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }
    /*
    metodo que obtiene query del objeto divisa
    */
    public int getDivisaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Divisa> rt = cq.from(Divisa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

}
