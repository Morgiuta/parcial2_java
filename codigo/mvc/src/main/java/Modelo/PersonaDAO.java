/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import Modelo.Persona;

/**
 * @author francisco
 */

public class PersonaDAO{

    public boolean insertarPersona(Connection conn, Persona persona) {
        String sql = "INSERT INTO persona (pers_documento, pers_nombre) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, persona.getDni());
            ps.setString(2, persona.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
           e.printStackTrace();
           return false;
        }
    }

    public boolean buscarPersona(Connection conn, String documento) {
        String sql = "SELECT pers_documento, pers_nombre FROM persona WHERE persona = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, documento);
            ps.executeQuery();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
