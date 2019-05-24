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
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author HTTP
 */
public class DivisaJpaController implements Serializable {

    public DivisaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Divisa divisa) throws RollbackFailureException, Exception {
        if (divisa.getTransaccionList() == null) {
            divisa.setTransaccionList(new ArrayList<Transaccion>());
        }
        if (divisa.getHistorialList() == null) {
            divisa.setHistorialList(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
            for (Transaccion transaccionListTransaccionToAttach : divisa.getTransaccionList()) {
                transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(), transaccionListTransaccionToAttach.getId());
                attachedTransaccionList.add(transaccionListTransaccionToAttach);
            }
            divisa.setTransaccionList(attachedTransaccionList);
            List<Historial> attachedHistorialList = new ArrayList<Historial>();
            for (Historial historialListHistorialToAttach : divisa.getHistorialList()) {
                historialListHistorialToAttach = em.getReference(historialListHistorialToAttach.getClass(), historialListHistorialToAttach.getId());
                attachedHistorialList.add(historialListHistorialToAttach);
            }
            divisa.setHistorialList(attachedHistorialList);
            em.persist(divisa);
            for (Transaccion transaccionListTransaccion : divisa.getTransaccionList()) {
                Divisa oldDivisaIdOfTransaccionListTransaccion = transaccionListTransaccion.getDivisaId();
                transaccionListTransaccion.setDivisaId(divisa);
                transaccionListTransaccion = em.merge(transaccionListTransaccion);
                if (oldDivisaIdOfTransaccionListTransaccion != null) {
                    oldDivisaIdOfTransaccionListTransaccion.getTransaccionList().remove(transaccionListTransaccion);
                    oldDivisaIdOfTransaccionListTransaccion = em.merge(oldDivisaIdOfTransaccionListTransaccion);
                }
            }
            for (Historial historialListHistorial : divisa.getHistorialList()) {
                Divisa oldDivisaIdOfHistorialListHistorial = historialListHistorial.getDivisaId();
                historialListHistorial.setDivisaId(divisa);
                historialListHistorial = em.merge(historialListHistorial);
                if (oldDivisaIdOfHistorialListHistorial != null) {
                    oldDivisaIdOfHistorialListHistorial.getHistorialList().remove(historialListHistorial);
                    oldDivisaIdOfHistorialListHistorial = em.merge(oldDivisaIdOfHistorialListHistorial);
                }
            }
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

    public void edit(Divisa divisa) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Divisa persistentDivisa = em.find(Divisa.class, divisa.getId());
            List<Transaccion> transaccionListOld = persistentDivisa.getTransaccionList();
            List<Transaccion> transaccionListNew = divisa.getTransaccionList();
            List<Historial> historialListOld = persistentDivisa.getHistorialList();
            List<Historial> historialListNew = divisa.getHistorialList();
            List<String> illegalOrphanMessages = null;
            for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
                if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transaccion " + transaccionListOldTransaccion + " since its divisaId field is not nullable.");
                }
            }
            for (Historial historialListOldHistorial : historialListOld) {
                if (!historialListNew.contains(historialListOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialListOldHistorial + " since its divisaId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
                transaccionListNewTransaccionToAttach = em.getReference(transaccionListNewTransaccionToAttach.getClass(), transaccionListNewTransaccionToAttach.getId());
                attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
            }
            transaccionListNew = attachedTransaccionListNew;
            divisa.setTransaccionList(transaccionListNew);
            List<Historial> attachedHistorialListNew = new ArrayList<Historial>();
            for (Historial historialListNewHistorialToAttach : historialListNew) {
                historialListNewHistorialToAttach = em.getReference(historialListNewHistorialToAttach.getClass(), historialListNewHistorialToAttach.getId());
                attachedHistorialListNew.add(historialListNewHistorialToAttach);
            }
            historialListNew = attachedHistorialListNew;
            divisa.setHistorialList(historialListNew);
            divisa = em.merge(divisa);
            for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
                if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
                    Divisa oldDivisaIdOfTransaccionListNewTransaccion = transaccionListNewTransaccion.getDivisaId();
                    transaccionListNewTransaccion.setDivisaId(divisa);
                    transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
                    if (oldDivisaIdOfTransaccionListNewTransaccion != null && !oldDivisaIdOfTransaccionListNewTransaccion.equals(divisa)) {
                        oldDivisaIdOfTransaccionListNewTransaccion.getTransaccionList().remove(transaccionListNewTransaccion);
                        oldDivisaIdOfTransaccionListNewTransaccion = em.merge(oldDivisaIdOfTransaccionListNewTransaccion);
                    }
                }
            }
            for (Historial historialListNewHistorial : historialListNew) {
                if (!historialListOld.contains(historialListNewHistorial)) {
                    Divisa oldDivisaIdOfHistorialListNewHistorial = historialListNewHistorial.getDivisaId();
                    historialListNewHistorial.setDivisaId(divisa);
                    historialListNewHistorial = em.merge(historialListNewHistorial);
                    if (oldDivisaIdOfHistorialListNewHistorial != null && !oldDivisaIdOfHistorialListNewHistorial.equals(divisa)) {
                        oldDivisaIdOfHistorialListNewHistorial.getHistorialList().remove(historialListNewHistorial);
                        oldDivisaIdOfHistorialListNewHistorial = em.merge(oldDivisaIdOfHistorialListNewHistorial);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = divisa.getId();
                if (findDivisa(id) == null) {
                    throw new NonexistentEntityException("The divisa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
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

    public List<Divisa> findDivisaEntities() {
        return findDivisaEntities(true, -1, -1);
    }

    public List<Divisa> findDivisaEntities(int maxResults, int firstResult) {
        return findDivisaEntities(false, maxResults, firstResult);
    }

    private List<Divisa> findDivisaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
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
        EntityManager em = getEntityManager();
        try {
            return em.find(Divisa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDivisaCount() {
        EntityManager em = getEntityManager();
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
