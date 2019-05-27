/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import com.mycompany.interfaz.TransaccionBeanLocal;
import com.mycompany.pojo.TransaccionP;
import com.mycompany.pojo.UsuarioP;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Valeria
 */
@RequestScoped
@Path("transaccion")
public class TransaccionServer {

    @EJB
    TransaccionBeanLocal trans;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("comprar")
    public Response comprar(@HeaderParam("token-auto") String token,TransaccionP nuevo) {
        //String token = requestContext.getHeaderString("token-auto");
        System.out.println(token);
        //int id = seguridad.validarToken(token);
        int id = 1;
        nuevo.setUserId(id);
        JsonObject rest;
        trans.comprar(nuevo);
        rest = Json.createObjectBuilder().add("respuesta", "Compra exitosa").build();
        return Response.status(Response.Status.OK).entity(rest).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("actualizar")
    public Response listarTransaccion(ContainerRequestContext requestContext) {
        String token = requestContext.getHeaderString("token-auto");
        System.out.println("Actualizar:"+token);
        //int id = seguridad.validarToken(token);
        int id = 1;
        
        trans.actualizar();
        
        System.out.println("paso--");
        List<TransaccionP> datos = trans.listarTrans(id);
        if (datos.size() > 0) {
            return Response.status(Response.Status.OK).entity(datos).build();
        } else {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "No tiene trasacciones activas").build();
            return Response.status(Response.Status.NOT_FOUND).entity(rest).build();
        }

    }
    

}
