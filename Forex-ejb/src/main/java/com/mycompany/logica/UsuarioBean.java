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
    public int login(String username, String pass) {

        UsuarioJpaController dao = new UsuarioJpaController();
        List<Usuario> lista = dao.findUsuarioEntities();

        for (Usuario lista1 : lista) {
            if (lista1.getNameUser().equals(username) && lista1.getPassword().equals(pass)) {
                dao.em.close();
                return lista1.getId();
            }
        }
        dao.em.close();
        return -1;
    }

    @Override
    public void actualizar(UsuarioP user) {

        UsuarioJpaController dao = new UsuarioJpaController();
        dao.edit(user);
        dao.em.close();
    }

    @Override
    public boolean validaUserName(String username, int id) {
        UsuarioJpaController dao = new UsuarioJpaController();
        List<Usuario> lista = dao.findUsuarioEntities();

        for (Usuario lista1 : lista) {
            if (lista1.getNameUser().equals(username) || lista1.getId() == (id)) {
                dao.em.close();
                return true;
            }
        }
        dao.em.close();
        return false;
    }

    @Override
    public void registrar(UsuarioP user) {
        UsuarioJpaController dao = new UsuarioJpaController();
        Usuario nuevo = new Usuario(user.getId(), user.getName(), user.getNameUser(), user.getEmail(), user.getPassword());
        nuevo.setOutlay(user.getOutlay());
        nuevo.setEarnings(0d);
        dao.create(nuevo);
        dao.em.close();
    }

    @Override
    public UsuarioP perfil(int id) {
        int a = 0;
        UsuarioJpaController dao = new UsuarioJpaController();
        Usuario resul = dao.findUsuario(id);
        UsuarioP usar = new UsuarioP();
        usar.setId(resul.getId());
        usar.setName(resul.getName());
        usar.setNameUser(resul.getNameUser());
        usar.setEmail(resul.getEmail());
        usar.setPassword(resul.getPassword());
        usar.setOutlay(resul.getOutlay());
        usar.setEarnings(resul.getEarnings());
        dao.em.close();

        return usar;
    }

    @Override
    public double inversion(int id) {
        UsuarioJpaController dao = new UsuarioJpaController();
        Usuario resul = dao.findUsuario(id);
        dao.em.close();
        return resul.getOutlay();

    }

}
