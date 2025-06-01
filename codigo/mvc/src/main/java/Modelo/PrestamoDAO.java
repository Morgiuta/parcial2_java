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

    public boolean insertarPrestamo(Connection conn, Prestamo prestamo,int libroId,int personaId) {
        String sql = "INSERT INTO prestamo (pers_id,libr_id,prestamo_numero, prestamo_dia,prestamo_duracion) VALUES (?,?,?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, personaId);
            ps.setInt(2, libroId);   
            ps.setInt(3, prestamo.getNumero());
            ps.setDate(4, (Date) prestamo.getDia_prestamo());
            ps.setDate(5, (Date) prestamo.getDevolucion());
            ps.executeUpdate();
            System.out.println("Prestamo ingresado correctamente");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

}}
