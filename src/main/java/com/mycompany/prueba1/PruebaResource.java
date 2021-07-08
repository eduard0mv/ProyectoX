/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prueba1;

import com.mycompany.mysql.ConexionMySql;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Usuario
 */
@Path("Prueba")
@RequestScoped
public class PruebaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PruebaResource
     */
    public PruebaResource() {
    }
    
    @GET
    @Path("getJson")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        String temp = ConexionMySql.ConexionMySql();
        return temp;//"{'nombre': 'Eduardo'}";
    }

    /**
     * PUT method for updating or creating an instance of PruebaResource
     * @param content representation for the resource
     */
    @PUT
    @Path("putJson")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
