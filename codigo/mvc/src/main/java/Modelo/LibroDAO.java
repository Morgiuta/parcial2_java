package Modelo;
import java.sql.*;
import Modelo.Libro;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tgarc
 */
public class LibroDAO {
    
    public boolean insertarLibro(Connection conn, Libro libro) {
        String sql = "INSERT INTO libro (libr_titulo, libr_clasificacion, libr_numero) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getClasificacion());
            ps.setInt(3, libro.getNumero());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
           e.printStackTrace();
           return false;
        }
    }

    public boolean buscarLibro(Connection conn, String documento) {
        String sql = "SELECT libr_titulo, libr_clasificacion, libr_numero FROM libro WHERE libro = ?";
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
