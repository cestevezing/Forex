/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaz;

import javax.ejb.Local;

/**
 *
 * @author HTTP
 */
@Local
public interface TransaccionBeanLocal {
    public void listarTrans();
    public void comprar();
    public void vender();
}
