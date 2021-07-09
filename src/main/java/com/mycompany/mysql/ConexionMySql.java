/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class ConexionMySql {
    
    private static Connection conn;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/";
    
    public static Connection ConexionMySql() throws SQLException{
        conn = DriverManager.getConnection (url);

        try {
            Statement Stmt = conn.createStatement ();

            try {
                try {
                    Stmt.execute (" CREATE DATABASE pruebasDB; ");
                    Stmt.execute (" CREATE TABLE usuario (ID int NOT NULL, Nombre varchar(50) "
                            + " Apellido varchar(50), edad int, sexo varchar(15), telefono varchar(20), "
                            + " PRIMARY KEY(ID)); ");
                    conn.commit ();
                } catch (SQLException exception) {
                    // Okay if database exists
                    Stmt.execute("OPEN DATABASE pruebasDB; ");
                }

            } finally {
                Stmt.close ();
            }

        } catch (SQLException exception) {
            System.err.println ("SQLException : " + exception.toString ());
        }
        
        return conn;
    }
    
    public static void cerrar() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
    
    
}
