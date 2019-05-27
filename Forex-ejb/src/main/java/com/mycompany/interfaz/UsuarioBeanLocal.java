/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interfaz;

import com.mycompany.pojo.UsuarioP;
import javax.ejb.Local;

/**
 *
 * @author HTTP
 */
@Local
public interface UsuarioBeanLocal {
    public int login(String username,String pass);
    public void actualizar(UsuarioP user );
    public boolean validaUserName(String username,int id);
    public void registrar(UsuarioP user);
    public UsuarioP perfil(int id);
    public double inversion(int id);
    
}
