
package Controlador;

import Modelo.Conexion;
import Modelo.Persona;
import Modelo.Prestamo;
import Modelo.Libro;
import Modelo.LibroDAO;
import Modelo.PersonaDAO;
import Modelo.PrestamoDAO;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Controlador implements ActionListener {
    private Persona persona;
    private Prestamo prestamo;
    private Libro libro;
    private Vista view;
    private PrestamoDAO prestamoDAO;
    private LibroDAO libroDAO;

    public Controlador(){
        
    }
    public Controlador(Persona persona, Prestamo prestamo, Libro libro,Vista view) {
        this.persona = persona;
        this.prestamo = prestamo;
        this.libro = libro;
        this.view = view;
    }
    
    public void iniciar(){
        view.setTitle("MVC Prueba");
        view.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        persona.nombre = view.usuario.getText();


        //persona.setNumeroUno(Integer.parseInt(view.usuario.getText()))
        //model.setNumeroDos(Integer.parseInt(view.usuario.getText()));
    }
    
    public int login(String nombre, int dni){
        Persona persona = new Persona(dni,nombre);
        PersonaDAO dao = new PersonaDAO();

        try (Connection conn = Conexion.getConnection()){
            int respuesta = dao.buscarPersona(conn, persona);

            if (respuesta != 0){
                System.out.println("Persona Encontrada");
            } else {
                System.out.println("No se encontro al usuario");
            }
            return respuesta;
        } catch (SQLException e) {
            System.out.println("Error al consultar a la base de datos");
            return 0;
        }
    }
    
    public boolean singIn(String nombre, int dni){
        Persona persona = new Persona(dni, nombre);
        PersonaDAO dao = new PersonaDAO();

        try (Connection conn = Conexion.getConnection()){
            boolean respuesta = dao.insertarPersona(conn, persona);

            if (respuesta){
                System.out.println("Persona insertada con exito");
            } else {
                System.out.println("Error insertando Persona");
            }
            return respuesta;
        } catch (Exception e){
            System.out.println("Error la conectarse a la base de datos");
            return false;
        }
    }


    public int buscarLibros(String titulo) {
        Libro libro = new Libro();
        LibroDAO libroDAO = new LibroDAO();
    try (Connection conn = Conexion.getConnection()){
            int respuesta = libroDAO.buscarLibro(conn, titulo);

            if (respuesta != 0){
                System.out.println("libro insertados con exito");
            } else {
                System.out.println("Error insertando libros");
            }
            return respuesta;
        } catch (SQLException e) {
    System.out.println("Error en insertarLibros: " + e.getMessage());
    e.printStackTrace();
    return -1;
        }
   }

    public boolean crearPrestamo(String libroTitulo, int personaId, java.sql.Date fechaHoy, java.sql.Date fechaDevolucion) {
        int numeroPrestamo = 1;

        try (Connection conn = Conexion.getConnection()) {
            LibroDAO libroDao = new LibroDAO();
            int libroId = libroDao.buscarLibro(conn, libroTitulo);


            Libro libro = libroDao.getLibroPorId(conn, libroId);

            ArrayList<Libro> libros = new ArrayList<>();
            libros.add(libro);

            PersonaDAO personaDao = new PersonaDAO();
            Persona socio = personaDao.getPersona(conn, personaId);

            Prestamo prestamo = new Prestamo(numeroPrestamo, fechaHoy, fechaDevolucion, socio, libros);

            PrestamoDAO presDao = new PrestamoDAO();
            boolean respuesta = presDao.insertarPrestamo(conn, prestamo, libroId, personaId);

            if (respuesta) {
                System.out.println("Prestamo insertado con éxito");
            } else {
                System.out.println("Error insertando Prestamo");
            }
            return respuesta;
        } catch (SQLException e) {
            System.out.println("Error en crearPrestamo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Prestamo> getPrestamos(int personaId) {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PrestamoDAO dao = new PrestamoDAO();
            prestamos = dao.getPrestamosPorPersona(conn, personaId); // Llama al método que implementaste recién
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    public boolean devolverPrestamo(int personaId, int libroId) {
        try (Connection conn = Conexion.getConnection()) {
            PrestamoDAO dao = new PrestamoDAO();
            return dao.libroDevuelto(conn, personaId, libroId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
