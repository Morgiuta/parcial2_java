/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;


/**
 * @author tomimrc
 */

public class PrestamoDAO{

    public boolean insertarPrestamo(Connection conn, Prestamo prestamo,Libro libro,Persona persona) {
        String sql = "INSERT INTO prestamo (prestamo_numero, prestamo_dia,prestamo_duracion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, prestamo.getNumero());
            ps.setDate(2, (Date) prestamo.getDia_prestamo());
            ps.setDate(3, (Date) prestamo.getDevolucion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean buscarPersona(Connection conn, Persona persona) {
        String sql = "SELECT pers_documento, pers_nombre FROM persona WHERE pers_documento = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, persona.getDni());
            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                System.out.println("Hay resultados");
                // Si querés acceder a los datos:
                String nombre = resultado.getString("pers_nombre"); // ejemplo
                System.out.println("Nombre: " + nombre);
                return true;
            } else {
                System.out.println("No hay resultados");
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
