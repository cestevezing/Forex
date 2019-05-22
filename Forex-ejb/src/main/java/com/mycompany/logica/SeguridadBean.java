/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.interfaz.SeguridadBeanLocal;
import javax.ejb.Stateless;

/**
 *@author HTTP
 */
@Stateless
public class SeguridadBean implements SeguridadBeanLocal {

    @Override
    public boolean validarToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregarToken() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
