/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaz;

import com.mycompany.pojo.TransaccionP;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HTTP
 */
@Local
public interface TransaccionBeanLocal {
    public List<TransaccionP> listarTrans(int userId);
    public void actualizar();
    public void comprar(TransaccionP trans);
    public void vender(int id);
}
