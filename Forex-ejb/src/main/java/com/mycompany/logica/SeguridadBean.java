/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.controller.SeguridadJpaController;
import com.mycompany.entidades.Seguridad;
import com.mycompany.interfaz.SeguridadBeanLocal;
import java.util.List;
import javax.ejb.Stateless;

/**
 * @author Valeria y Cristian
 */
@Stateless
public class SeguridadBean implements SeguridadBeanLocal {
    /**
     * Constructor vacio
     */
    public SeguridadBean() {
    }
    /**
     * 
     * @param token
     * @return 
     */
    @Override
    public int validarToken(String token) {
        SeguridadJpaController dao = new SeguridadJpaController();
        List<Seguridad> lista = dao.findSeguridadEntities();
        for (Seguridad lista1 : lista) {
            if (lista1.getToken().equals(token)) {
                dao.em.close();
                return lista1.getUser();
            }
        }
        dao.em.close();
        return -1;
    }
    /**
     * 
     * @param token
     * @param user 
     */
    @Override
    public void agregarToken(String token, int user) {
        SeguridadJpaController dao = new SeguridadJpaController();
        try {
            
            Seguridad seg = new Seguridad();
            seg.setToken(token);
            seg.setUser(user);

            dao.create(seg);
            System.out.println("no ingreso");
        } catch (Exception e) {
            dao.em.close();
            System.out.println("error" + e.getMessage());
        }
        dao.em.close();

    }

}
