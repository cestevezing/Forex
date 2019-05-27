/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.controller.DivisaJpaController;
import com.mycompany.controller.HistorialJpaController;
import com.mycompany.controller.TransaccionJpaController;
import com.mycompany.controller.UsuarioJpaController;
import com.mycompany.entidades.Divisa;
import com.mycompany.entidades.Historial;
import com.mycompany.entidades.Transaccion;
import com.mycompany.interfaz.TransaccionBeanLocal;
import com.mycompany.pojo.TransaccionP;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;

/**
 *
 * @author HTTP
 */
@Stateless
public class TransaccionBean implements TransaccionBeanLocal {

    @Override
    public void vender() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void comprar(TransaccionP trans) {
        UsuarioJpaController user = new UsuarioJpaController();
        DivisaJpaController div = new DivisaJpaController();
        TransaccionJpaController dao = new TransaccionJpaController();

        Divisa di = div.findDivisa(trans.getDivisaId());

        Transaccion nuevo = new Transaccion();
        nuevo.setUserId(user.findUsuario(trans.getUserId()));
        nuevo.setActual(di.getValue());
        nuevo.setBase(di.getValue());
        nuevo.setDivisaId(di);
        nuevo.setState(false);
        nuevo.setValuePip(trans.getValuePip());

        dao.create(nuevo);

    }

    @Override
    public List<TransaccionP> listarTrans(int userId) {
        List<TransaccionP> salida = new ArrayList<>();
        TransaccionJpaController dao = new TransaccionJpaController();

        for (Transaccion trans : dao.findTransaccionEntities()) {
            if (trans.getUserId().getId() == userId && trans.getState() == false) {

                TransaccionP aux = new TransaccionP(trans.getId(), trans.getUserId().getId(), trans.getDivisaId().getId(), trans.getBase(), trans.getActual(), trans.getState(), trans.getValuePip());
                salida.add(aux);
            }

        }
        return salida;
    }

    @Override
    public void actualizar() {
        DivisaJpaController div = new DivisaJpaController();
        TransaccionJpaController dao = new TransaccionJpaController();
        
        Random rnd = new Random();
        int numero = rnd.nextInt(7) - 3;
        List<Divisa> listaDiv = div.findDivisaEntities();

        for (Divisa nuevo : listaDiv) {

            double decimal =  ((numero*1.00000) / 100000);
            double valor = nuevo.getValue()+decimal;
                        
            nuevo.setValue(valor);
            div.edit(nuevo);
            
            for (Transaccion trans : dao.findTransaccionEntities()) {
                if (trans.getDivisaId().getId() == nuevo.getId() && trans.getState() == false) {
                    trans.setActual(valor);
                    dao.edit(trans);
                }
            }
                        
            HistorialJpaController his = new HistorialJpaController();
            Historial h = new Historial();
            h.setDivisaId(nuevo);
            h.setValor(valor);
            his.create(h);
            valor = 0;

        }
        div.em.close();
        dao.em.close();
        numero = 0;
    }

}
