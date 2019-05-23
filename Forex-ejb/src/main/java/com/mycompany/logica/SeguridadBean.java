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
 *@author HTTP
 */
@Stateless
public class SeguridadBean implements SeguridadBeanLocal {
    
    
    @Override
    public int validarToken(String token) {
        SeguridadJpaController dao = new SeguridadJpaController();
        List<Seguridad> lista = dao.findSeguridadEntities();
        for (Seguridad lista1 : lista) {
            if(lista1.getToken().equals(token)){
                return lista1.getUser();
            }
        }
        return -1;
    }

    @Override
    public void agregarToken(String token, int user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
