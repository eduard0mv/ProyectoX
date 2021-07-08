/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionMySql {
    
    private static Connection conn;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String pwd = "root";
    private static String url = "jdbc:mysql://localhost:3306/usuario";
    
    public static String ConexionMySql(){
        String temp = "";
        conn = null;
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                temp = "exito";
                System.out.println(1);
                //System.out.println("Connection Successful!");
            } else {
                temp = "fracaso";
                System.out.println(0);
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar "+e);
        }
        
        return temp;
    }
    
    public static void cerrar() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
    
    
}
