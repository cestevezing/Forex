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
import com.mycompany.entidades.Usuario;
import com.mycompany.interfaz.TransaccionBeanLocal;
import com.mycompany.pojo.TransaccionP;
import com.mycompany.pojo.UsuarioP;
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

    public TransaccionBean() {
    }
    
    @Override
    public void vender(int id) {
        TransaccionJpaController dao = new TransaccionJpaController();
        Transaccion lista = dao.findTransaccion(id);
        lista.setState(true);
        
        
        UsuarioJpaController user = new UsuarioJpaController();
        Usuario usuario = user.findUsuario(lista.getUserId().getId());
        usuario.setOutlay(usuario.getOutlay() + ((lista.getActual() - lista.getBase()) * lista.getValuePip()*100000.00));
        System.out.println("el valor es: " + ((lista.getActual() - lista.getBase()) * lista.getValuePip()*100000.00));
        user.edit(new UsuarioP(usuario.getId(), usuario.getName(), usuario.getNameUser(), usuario.getEmail(), usuario.getPassword(), usuario.getOutlay(), 0));
        dao.edit(lista);
        
        dao.em.close();
    }
    
    @Override
    public void comprar(TransaccionP trans) {
        UsuarioJpaController user = new UsuarioJpaController();
        DivisaJpaController div = new DivisaJpaController();
        TransaccionJpaController dao = new TransaccionJpaController();
        
        Divisa di = div.findDivisa(trans.getDivisaId());
        div.em.close();
        
        Transaccion nuevo = new Transaccion();
        nuevo.setUserId(user.findUsuario(trans.getUserId()));
        nuevo.setActual(di.getValue());
        nuevo.setBase(di.getValue());
        nuevo.setDivisaId(di);
        nuevo.setState(false);
        nuevo.setValuePip(trans.getValuePip());
        
        dao.create(nuevo);        
        dao.em.close();       
        user.em.close();
        
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
        dao.em.close();
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
            
            double decimal = ((numero * 1.00000) / 100000);
            double valor = nuevo.getValue() + decimal;
            
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
