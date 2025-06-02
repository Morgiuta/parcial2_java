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
    
    public int buscarLibro(Connection conn, String libro) {
        String sql = "SELECT libr_id,libr_titulo, libr_clasificacion, libr_numero FROM libro WHERE libr_titulo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, libro);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int libroId = rs.getInt("libr_id");
                String libroNombre = rs.getString("libr_titulo");
                String libroClasificacion = rs.getString("libr_clasificacion");
                int libroNumero = rs.getInt("libr_numero");

                System.out.println("ID: "+libroId +" "+ libroNombre + "" + libroClasificacion+ ""+ libroNumero );
                return libroId;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public Libro getLibroPorId(Connection conn, int libroId) {
        String sql = "SELECT libr_titulo, libr_clasificacion, libr_numero FROM libro WHERE libr_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String titulo = rs.getString("libr_titulo");
                String clasificacion = rs.getString("libr_clasificacion");
                int numero = rs.getInt("libr_numero");
                return new Libro(titulo, clasificacion, numero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
