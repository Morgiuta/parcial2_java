
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
    // public Controlador(Persona persona, Prestamo prestamo, Libro libro,Vista view, ) {
       // this.persona = persona;
        //this.prestamo = prestamo;
       // this.libro = libro;
     //   this.view = view;
   //     this.prestamoDAO = prestamoDAO;
 //   }
 
  
    
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
    

    public boolean cargarLibros() {
        Libro libro = new Libro();
        LibroDAO libroDAO = new LibroDAO();
    try (Connection conn = Conexion.getConnection()){
            boolean respuesta = libroDAO.insertarLibros(conn);

            if (respuesta){
                System.out.println("libro insertados con exito");
            } else {
                System.out.println("Error insertando libros");
            }
            return respuesta;
        } catch (SQLException e) {
    System.out.println("Error en insertarLibros: " + e.getMessage());
    e.printStackTrace(); // Mostrará línea y causa del error exacto
    return false;
        }
   }
    
    public boolean crearPrestamo(){
        Prestamo prestamo = new Prestamo();
        PrestamoDAO presDao = new PrestamoDAO();

        try (Connection conn = Conexion.getConnection()){
            boolean respuesta = presDao.insertarPrestamo(conn, prestamo, libro, persona);

            if (respuesta){
                System.out.println("Prestamo insertada con exito");
            } else {
                System.out.println("Error insertando Prestamo");
            }
            return respuesta;
        } catch (SQLException e) {
    System.out.println("Error en crearPrestamo: " + e.getMessage());
    e.printStackTrace(); // Mostrará línea y causa del error exacto
    return false;
        }
    }
}
;