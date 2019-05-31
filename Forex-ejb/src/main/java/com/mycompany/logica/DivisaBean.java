/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.interfaz.DivisaBeanLocal;
import javax.ejb.Stateless;

/**
 *
 * @author Valeria y Cristian
 */
@Stateless
public class DivisaBean implements DivisaBeanLocal {
    
    /*
    Constructor vacio
    */
    
    public DivisaBean() {
    }

    /*
    Implementacion por defecto de los metodos de la interface
    */
    @Override
    public void actualizaValor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    Implementacion por defecto de los metodos de la interface
    */
    @Override
    public void listarDiv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
