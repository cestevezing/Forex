/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;



import com.mycompany.interfaz.UsuarioBeanLocal;
import com.mycompany.pojo.UsuarioP;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HTTP
 */
@RequestScoped
@Path("prueba")
public class UsuerioServer {

    @EJB
    private UsuarioBeanLocal user;
    
      
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{user}/{pass}")
    public Response login(@PathParam("user")String usuar,@PathParam("pass")String pass) {
                        
        if (user.login(usuar,pass)) {
            return Response.status(Response.Status.OK).entity("ingreso").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("no ingreso").build();
        }        
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registro")
    public Response registar(UsuarioP nuevo){
        if(!user.validaUserName(nuevo.getNameUser(),nuevo.getId())){
            user.registrar(nuevo);
            return Response.status(Response.Status.OK).entity("Usuario Registrado con exito").build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("El User Name ya existe").build();
        } 
    }
    
    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("perfil")
    public Response perfil(){
        
    }*/
    
}
