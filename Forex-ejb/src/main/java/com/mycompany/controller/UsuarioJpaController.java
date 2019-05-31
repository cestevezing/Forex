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
import com.mycompany.entidades.Usuario;
import com.mycompany.pojo.UsuarioP;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author Valeria y Cristian
 */
public class UsuarioJpaController implements Serializable {
    /*
    Constructor de la clase que controla Usuario desde JPA
    */
    public UsuarioJpaController() {
        this.utx = utx;
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    private UserTransaction utx;
    public EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }
    /*
    Metodo que crea
    */
    public void create(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println("error");
            throw ex;
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }
    /*
    Metodo que edita
    */
    public void edit(UsuarioP usuario) {
        try {
            em.getTransaction().begin();
            Usuario user = em.find(Usuario.class, usuario.getId());

            user.setName(usuario.getName());
            user.setPassword(usuario.getPassword());
            user.setEmail(usuario.getEmail());
            user.setOutlay(usuario.getOutlay());

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
            Usuario user = em.find(Usuario.class, id);
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }
    /*
    Metodo que retorna entidades
    */
    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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
    Metodo que retorna Entidad Usuario
    */
    public Usuario findUsuario(Integer id) {
        try {
            return em.find(Usuario.class, id);
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }
    /*
    Metodo que obtiene query
    */
    public int getUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
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
