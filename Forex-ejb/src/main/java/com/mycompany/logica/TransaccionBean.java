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
 * @author Valeria y Cristian
 */
@Stateless
public class TransaccionBean implements TransaccionBeanLocal {
    /**
     * Constructor vacio
     */
    public TransaccionBean() {
    }
    /**
     * Metodo utilizado para realizar la operacion vender
     * @param id 
     */
    @Override
    public void vender(int id) {
        TransaccionJpaController dao = new TransaccionJpaController();
        Transaccion lista = dao.findTransaccion(id);
        lista.setState(true);

        UsuarioJpaController user = new UsuarioJpaController();
        Usuario usuario = user.findUsuario(lista.getUserId().getId());
        usuario.setOutlay(usuario.getOutlay() + ((lista.getActual() - lista.getBase()) * lista.getValuePip() * 100000.00));
        UsuarioP usar = new UsuarioP();
        usar.setId(usuario.getId());
        usar.setName(usuario.getName());
        usar.setNameUser(usuario.getNameUser());
        usar.setEmail(usuario.getEmail());
        usar.setPassword(usuario.getPassword());
        usar.setOutlay(usuario.getOutlay());
        usar.setEarnings(0d);
        user.edit(usar);
        dao.edit(lista);
        
        user.em.close();
        dao.em.close();
    }
    /**
     * Metodo utilizado para realizar la operacion comprar
     * @param trans 
     */
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
    /**
     * Metodo utilizado para listar transacciones
     * @param userId
     * @return 
     */
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
    
    /**
     * Metodo utilizado para listar transacciones realizadas
     * @param id
     * @return 
     */
    @Override
    public List<TransaccionP> listaTransRealizadas(int id) {
        List<TransaccionP> salida = new ArrayList<>();
        TransaccionJpaController dao = new TransaccionJpaController();

        for (Transaccion trans : dao.findTransaccionEntities()) {
            if (trans.getUserId().getId() == id && trans.getState() == true) {

                TransaccionP aux = new TransaccionP(trans.getId(), trans.getUserId().getId(), trans.getDivisaId().getId(), trans.getBase(), trans.getActual(), trans.getState(), trans.getValuePip());
                salida.add(aux);
            }

        }
        dao.em.close();
        return salida;

    }
    /**
     * Metodo utilizado para actualizar
     */
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

            valor = 0;
        }
        div.em.close();
        dao.em.close();

        numero = 0;
    }
/**
 * Metodo utilizado para generar historial
 * @param userId
 * @return 
 */
    @Override
    public List<TransaccionP> historial(int userId) {
        List<TransaccionP> salida = new ArrayList();
        TransaccionJpaController dao = new TransaccionJpaController();
        int cont = 0;
        for (Transaccion trans : dao.findTransaccionEntities()) {
            if (trans.getState() == true && trans.getUserId().getId() == userId) {
                cont = cont + 1;

                TransaccionP aux = new TransaccionP(trans.getId(), cont, trans.getDivisaId().getId(), trans.getBase(),formatearDecimales((((trans.getActual() - trans.getBase()) * trans.getValuePip())*100000),2), trans.getState(), trans.getValuePip());
                salida.add(aux);
            }

        }
        dao.em.close();
        return salida;
    }
    /**
     * 
     * @param numero
     * @param numeroDecimales
     * @return 
     */
    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }
    /**
     * Metodo utilizado para finalizar operacion
     * @param id 
     */
    @Override
    public void fializar(int id) {
        TransaccionJpaController dao = new TransaccionJpaController();
        UsuarioJpaController user = new UsuarioJpaController();
        Usuario usuario = user.findUsuario(id);
        
        List<Transaccion> lista = dao.findTransaccionEntities();
        
        double total = 0;
        for (Transaccion trans : lista) {
            if(trans.getUserId().getId() == id && trans.getState()==false){
                trans.setState(true);
                total = total + ((trans.getActual() - trans.getBase()) * trans.getValuePip() * 100000.00);
            
                dao.edit(trans);
            }
        }
        
        usuario.setOutlay(usuario.getOutlay() + total);
        
        UsuarioP usar = new UsuarioP();
        usar.setId(usuario.getId());
        usar.setName(usuario.getName());
        usar.setNameUser(usuario.getNameUser());
        usar.setEmail(usuario.getEmail());
        usar.setPassword(usuario.getPassword());
        usar.setOutlay(usuario.getOutlay());
        usar.setEarnings(0d);
        
        user.edit(usar);
               
        user.em.close();
        dao.em.close();
    }

    
}
