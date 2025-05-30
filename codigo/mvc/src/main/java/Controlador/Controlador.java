
package Controlador;

import Modelo.Conexion;
import Modelo.Persona;
import Modelo.Prestamo;
import Modelo.Libro;
import Modelo.PersonaDAO;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Controlador implements ActionListener {
    private Persona persona;
    private Prestamo prestamo;
    private Libro libro;
    private Vista view;

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
    
    public void login(String nombre, int dni){
        
        Persona persona = new Persona(dni,nombre);
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
    
    
    public void bddConnection(){
        try {
      Class.forName("org.mariadb.jdbc.Driver");
      Connection conn = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/parcial_java", "root", "inolvidable");
      System.out.println("Conexión exitosa");
  } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
  }

    }
   
}
;