/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;

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

    public int buscarPersona(Connection conn, Persona persona) {
        String sql = "SELECT pers_id, pers_documento, pers_nombre FROM persona WHERE pers_documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, persona.getDni());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int personaId = rs.getInt("pers_id");
                return personaId;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public Persona getPersona(Connection conn, int id){
        String sql = "SELECT pers_nombre, pers_documento FROM persona WHERE pers_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                String nombre = resultado.getString("pers_nombre");
                int documento = resultado.getInt("pers_documento");
                return new Persona(documento, nombre);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
