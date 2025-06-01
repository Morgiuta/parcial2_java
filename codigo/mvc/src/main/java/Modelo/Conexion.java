/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author francisco
 */
public class Conexion { 
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/parcial_java";
    private static final String USER = "root";
    private static final String PASSWORD = "40972941";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar la base de datos", e);
        }

    }
}
