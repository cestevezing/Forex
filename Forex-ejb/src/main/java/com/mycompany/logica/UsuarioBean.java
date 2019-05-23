/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logica;

import com.mycompany.controller.UsuarioJpaController;
import com.mycompany.interfaz.UsuarioBeanLocal;
import com.mycompany.entidades.Usuario;
import com.mycompany.pojo.UsuarioP;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author HTTP
 */
@Stateless
public class UsuarioBean implements UsuarioBeanLocal {

    public UsuarioBean() {
    }

    @Override
    public boolean login(String username, String pass) {
        
        UsuarioJpaController dao = new UsuarioJpaController();
        List<Usuario> lista = dao.findUsuarioEntities();
        
        for (Usuario lista1 : lista) {
            if (lista1.getNameUser().equals(username) && lista1.getPassword().equals(pass)) {
                return true;
            }            
        }
        return false;
    }

    @Override
    public void actualizar(UsuarioP user) {
        
        UsuarioJpaController dao = new UsuarioJpaController();
        dao.edit(user);
        
    }

    @Override
    public boolean validaUserName(String username,int id) {
        UsuarioJpaController dao = new UsuarioJpaController();
        List<Usuario> lista = dao.findUsuarioEntities();
        
        for (Usuario lista1 : lista) {
            if (lista1.getNameUser().equals(username) || lista1.getId() == (id) ) {
                return true;
            }            
        }
        return false;
    }

    @Override
    public void registrar(UsuarioP user) {        
        UsuarioJpaController dao = new UsuarioJpaController();
        Usuario nuevo = new Usuario(user.getId(), user.getName(), user.getNameUser(), user.getEmail(), user.getPassword());
        dao.create(nuevo);
        
    }

    @Override
    public UsuarioP perfil(int id) {
        UsuarioJpaController dao = new UsuarioJpaController();
        Usuario resul = dao.findUsuario(id);
        return new UsuarioP(resul.getId(),resul.getName(), resul.getNameUser(), resul.getEmail(), resul.getPassword(), resul.getOutlay(), resul.getEarnings());
    }

    
}
