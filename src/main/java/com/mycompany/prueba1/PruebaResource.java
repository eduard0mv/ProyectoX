/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prueba1;

import com.mycompany.mysql.ConexionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import org.json.JSONObject;
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
    @Path("prueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String prueba() {
        return "{'nombre': 'Eduardo'}";
    }
    
    @GET
    @Path("usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario() throws SQLException, Exception {
        Connection cnx = ConexionMySql.ConexionMySql();
        ResultSet rs = null;
        
        PreparedStatement ps = cnx.prepareStatement(""
                + " SELECT ID, Nombre, Apellido, Edad, Sexo, Telefono "
                + " FROM usuario; ");
        
        rs = ps.executeQuery();
        
        String temp = "";
        
        while(rs.next()){
            JSONObject jsonobject = new JSONObject();
            
            jsonobject.put("id",rs.getInt("ID"));
            jsonobject.put("nombre",rs.getString("Nombre"));
            jsonobject.put("apellido",rs.getString("Apellido"));
            jsonobject.put("edad",rs.getInt("Edad"));
            jsonobject.put("sexo",rs.getString("Sexo"));
            jsonobject.put("telefono",rs.getString("Telefono"));
            
            temp += jsonobject.toString() + "\n";
        }
        
        ConexionMySql.cerrar();
        
        return temp;//"{'nombre': 'Eduardo'}";
    }

    /**
     * PUT method for updating or creating an instance of PruebaResource
     * @param content representation for the resource
     */
    @PUT
    @Path("usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertUsuario(String content) throws SQLException, Exception {
        JSONObject usuario = new JSONObject(content);
        Connection cnx = ConexionMySql.ConexionMySql();
        
        PreparedStatement ps = cnx.prepareStatement(""
                + " INSERT INTO usuario (Nombre, Apellido, Edad, Sexo, Telefono) "
                + " VALUES (?,?,?,?,?); ");
        
        ps.setString(1,usuario.getString("nombre"));
        ps.setString(2,usuario.getString("apellido"));
        ps.setInt(3,usuario.getInt("edad"));
        ps.setString(4,usuario.getString("sexo"));
        ps.setString(5,usuario.getString("telefono"));
        
        ps.executeUpdate();
        
        ConexionMySql.cerrar();
    }
}
