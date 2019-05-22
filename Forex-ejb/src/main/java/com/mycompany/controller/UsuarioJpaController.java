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

/**
 *
 * @author HTTP
 */
public class UsuarioJpaController implements Serializable {

    private EntityManager em = null;

    public UsuarioJpaController() {
        this.em = Persistence.createEntityManagerFactory("com.mycompany_Forex-ejb_ejb_1.0-SNAPSHOTPU").createEntityManager();
    }
    
    
    public EntityManager getEntityManager() {
        return em;
    }

    public void create(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void edit(UsuarioP usuario){
        try {
            em.getTransaction().begin();
            Usuario user = em.find(Usuario.class, usuario.getId());

            user.setName(usuario.getName());
            user.setPassword(usuario.getPassword());
            user.setEmail(usuario.getEmail());

            em.getTransaction().commit();
      
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            em.close();
        }
    }

    public void destroy(Integer id) {
        try {
            em.getTransaction().begin();
            Usuario user = em.find(Usuario.class, id);
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            em.close();
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

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
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
        
        
    }

    public int getUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
