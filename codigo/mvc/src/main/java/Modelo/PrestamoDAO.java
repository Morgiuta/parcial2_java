/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;


/**
 * @author tomimrc
 */

public class PrestamoDAO{

    public boolean insertarPrestamo(Connection conn, Prestamo prestamo, int libroId, int personaId) {
        String sql = "INSERT INTO prestamo (pers_id, libr_id, prestamo_numero, prestamo_dia, prestamo_duracion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personaId);
            ps.setInt(2, libroId);
            ps.setInt(3, prestamo.getNumero());
            ps.setDate(4, prestamo.getDia_prestamo());
            ps.setDate(5, prestamo.getDevolucion());
            ps.executeUpdate();
            System.out.println("Préstamo ingresado correctamente");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Prestamo> getPrestamosPorPersona(Connection conn, int personaId) {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String sql = """
        SELECT p.prestamo_numero, p.prestamo_dia, p.prestamo_duracion, p.prestamo_estado,
               l.libr_titulo, l.libr_clasificacion, l.libr_numero
        FROM prestamo p
        JOIN libro l ON p.libr_id = l.libr_id
        WHERE p.pers_id = ?
    """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Libro
                String titulo = rs.getString("libr_titulo");
                String clasificacion = rs.getString("libr_clasificacion");
                int numeroLibro = rs.getInt("libr_numero");
                Libro libro = new Libro(titulo, clasificacion, numeroLibro);

                // Fechas y número de préstamo
                int numeroPrestamo = rs.getInt("prestamo_numero");
                java.sql.Date diaPrestamo = rs.getDate("prestamo_dia");
                java.sql.Date devolucion = rs.getDate("prestamo_duracion");

                // Estado
                boolean estado = rs.getBoolean("prestamo_estado");

                // Constructor (adaptalo según tu modelo)
                ArrayList<Libro> libros = new ArrayList<>();
                libros.add(libro);
                Prestamo prestamo = new Prestamo(numeroPrestamo, diaPrestamo, devolucion, null, libros, estado);
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    public boolean libroDevuelto(Connection conn, int personaId, int libroId) {
        String sql = "UPDATE prestamo SET prestamo_estado = 1 WHERE pers_id = ? AND libr_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personaId);
            ps.setInt(2, libroId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
