/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaz;

import javax.ejb.Local;

/**
 *
 * @author Valeria y Cristian
 */
@Local
public interface DivisaBeanLocal {
    
    /*
    Declaracion de metodos
    */
    public void actualizaValor();
    public void listarDiv();

}
