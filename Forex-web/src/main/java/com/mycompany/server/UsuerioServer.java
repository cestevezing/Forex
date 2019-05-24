/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import com.mycompany.interfaz.SeguridadBeanLocal;
import com.mycompany.interfaz.UsuarioBeanLocal;
import com.mycompany.pojo.UsuarioP;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.container.ContainerRequestContext;
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
    private SeguridadBeanLocal seg;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{user}/{pass}")
    public Response login(@PathParam("user") String usuar, @PathParam("pass") String pass) {
        JsonObject rest;
        int id = user.login(usuar, pass);
        if (id > 0) {
            String token = issueToken(usuar);
            System.out.println("esto es =" + token);
            System.out.println(id);
            
            seg.agregarToken("hola", id);
                    
            
            rest = Json.createObjectBuilder().add("respuesta", "Ingreso").add("token", "prueba").build();
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
            rest = Json.createObjectBuilder().add("respuesta", "Usuario Registrado con exito").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } else {
            rest = Json.createObjectBuilder().add("respuesta", "El usuario ya existe").build();
            return Response.status(Response.Status.UNAUTHORIZED).entity(rest).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("perfil")
    public Response perfil(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString("token-auto");
        System.out.println(token);
        int id = seg.validarToken(token);
        UsuarioP resul = user.perfil(id);
        return Response.status(Response.Status.OK).entity(resul).build();

    }

    private String issueToken(String login){
        //Calculamos la fecha de expiración del token
        Date issueDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.MINUTE, 60);
        Date expireDate = calendar.getTime();

        //Creamos el token
        String token = Jwts.builder().setSubject(login)
                .setExpiration(new Date(2019,6,15)).setIssuer("nat@gmail.com")
                .claim("groups", new String[] {"user","nat"})
                .signWith(SignatureAlgorithm.HS512,"1234567").compact();
        return token;
        
    }
}
