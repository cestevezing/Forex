/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.controller.HistorialJpaController;
import com.mycompany.entidades.Historial;
import com.mycompany.interfaz.HistorialBeanLocal;
import com.mycompany.pojo.HistorialP;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * @author Valeria y Cristian
 */
@Stateless
public class HistorialBean implements HistorialBeanLocal {

    /*
    Constructor vacio
    */
    public HistorialBean() {
    }

    /*
    Implementacion por defecto de los metodos de la interface
    */
    @Override
    public void agregar() {
    }
    /*
    Implementacion por defecto de los metodos de la interface
    */
    @Override
    public void listarHistorial(int id) {
                
    }

}
