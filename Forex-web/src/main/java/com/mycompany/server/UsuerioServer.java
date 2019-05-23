/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import com.mycompany.interfaz.SeguridadBeanLocal;
import com.mycompany.interfaz.UsuarioBeanLocal;
import com.mycompany.pojo.UsuarioP;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("usuario")
public class UsuerioServer {

    @EJB
    private UsuarioBeanLocal user;
    private SeguridadBeanLocal seguridad;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{user}/{pass}")
    public Response login(@PathParam("user") String usuar, @PathParam("pass") String pass) {
        JsonObject rest;
        if (user.login(usuar, pass)) {
            rest = Json.createObjectBuilder().add("respuesta", "Ingreso").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } else {
            rest = Json.createObjectBuilder().add("respuesta", "No ingreso").build();
            return Response.status(Response.Status.UNAUTHORIZED).entity(rest).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registro")
    public Response registar(UsuarioP nuevo) {
        JsonObject rest;
        
        if (!user.validaUserName(nuevo.getNameUser(), nuevo.getId())) {
            user.registrar(nuevo);
            rest= Json.createObjectBuilder().add("respuesta", "Usuario Registrado con exito").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } else {
            rest = Json.createObjectBuilder().add("respuesta", "El usuario ya existe").build();
            return Response.status(Response.Status.UNAUTHORIZED).entity(rest).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("perfil")
    public Response perfil(@HeaderParam("token-auto") String token) {

        int id = seguridad.validarToken(token);
        UsuarioP resul = user.perfil(id);
        return Response.status(Response.Status.OK).entity(resul).build();

    }

}
