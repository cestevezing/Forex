/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import com.mycompany.interfaz.SeguridadBeanLocal;
import com.mycompany.interfaz.TransaccionBeanLocal;
import com.mycompany.pojo.TransaccionP;
import java.util.List;
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
 * @author Valeria
 */
@RequestScoped
@Path("transaccion")
public class TransaccionServer {

    @EJB
    TransaccionBeanLocal trans;

    @EJB
    SeguridadBeanLocal seguridad;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("comprar")
    /**
     * Metodo utilizado para comprar
     */
    public Response comprar(@HeaderParam("token-auto") String token, TransaccionP nuevo) {

        int id = seguridad.validarToken(token);
        nuevo.setUserId(id);
        trans.comprar(nuevo);
        JsonObject rest = Json.createObjectBuilder().add("respuesta", "Compra exitosa").build();
        return Response.status(Response.Status.OK).entity(rest).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("listar")
    /**
     * Metodo utilizado para listar transacciones realizada
     */
    public Response listarTransaccion(@HeaderParam("token-auto") String token) {

        int id = seguridad.validarToken(token);
        List<TransaccionP> datos = trans.listarTrans(id);
        if (datos.size() > 0) {
            return Response.status(Response.Status.OK).entity(datos).build();
        } else {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "No tiene trasacciones activas").build();
            return Response.status(Response.Status.NOT_FOUND).entity(rest).build();
        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("actualizar")
    /**
     * Metodo utilizado para actualizar
     */
    public Response actualizar(@HeaderParam("token-auto") String token) {

        trans.actualizar();
        JsonObject rest = Json.createObjectBuilder().add("respuesta", "ok").build();
        return Response.status(Response.Status.OK).entity(rest).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("realizadas")
    /**
     * Metodo utilizado para listar transacciones realizada
     */
    public Response realizdas(@HeaderParam("token-auto") String token) {

        int id = seguridad.validarToken(token);
        List<TransaccionP> data = trans.listaTransRealizadas(id);
        if (data.size() > 0) {
            return Response.status(Response.Status.OK).entity(data).build();
        } else {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "No tiene trasacciones activas").build();
            return Response.status(Response.Status.NOT_FOUND).entity(rest).build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/venta/{id}")
    /**
     * Metodo utilizado para realizar venta 
     */
    public Response vender(@HeaderParam("token-auto") String token, @PathParam("id") int id) {

        trans.vender(id);
        JsonObject rest = Json.createObjectBuilder().add("respuesta", "Venta con exito").build();
        return Response.status(Response.Status.OK).entity(rest).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("finalizar")
    /**
     * Metodo utilizado para finalizar operacion
     */
    public Response finalizar(@HeaderParam("token-auto") String token) {

        int id = seguridad.validarToken(token);
        trans.fializar(id);
        JsonObject rest = Json.createObjectBuilder().add("respuesta", "Se finalizaron todas las transacciones").build();
        return Response.status(Response.Status.OK).entity(rest).build();

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("historial")
    /**
     * Metodo utilizado para generar historial
     */
    public Response historial(@HeaderParam("token-auto") String token) {

        int id = seguridad.validarToken(token);
        List<TransaccionP> lista = trans.historial(id);
        return Response.status(Response.Status.OK).entity(lista).build();

    }

}
