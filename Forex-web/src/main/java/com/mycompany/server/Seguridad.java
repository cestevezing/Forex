/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import com.mycompany.interfaz.SeguridadBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author HTTP
 */
@Provider
@PreMatching
public class Seguridad implements ContainerRequestFilter {

    @EJB
    private SeguridadBeanLocal seguridad;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String url = requestContext.getUriInfo().getAbsolutePath().toString();
        if (url.contains("/api/usuario/login")) {
            return;
        }
        if (url.contains("/api/usuario/registro")) {
            return;
        }
        String token = requestContext.getHeaderString("token-auto");
        System.out.println(token);
        if (token == null) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "Token requerido").build();
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(rest).type(MediaType.APPLICATION_JSON).build());
        } else {
            int user = seguridad.validarToken(token);
            if (user < 0) {
                JsonObject rest = Json.createObjectBuilder().add("respuesta", "Token incorrecto").build();
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(rest).type(MediaType.APPLICATION_JSON).build());
            }
        }
    }

}
