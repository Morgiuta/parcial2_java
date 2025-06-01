/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author ASUS
 */
public class Prestamo {
    
    public int numero;
    public Date dia_prestamo;
    public Date devolucion;
    private Persona socio;
    private ArrayList<Libro> prestado;


    
    public Prestamo(){}

    public Prestamo(int numero, Date dia_prestamo, Date devolucion, Persona socio, ArrayList<Libro> prestado) {
        this.numero = numero;
        this.dia_prestamo = dia_prestamo;
        this.devolucion = devolucion;
        this.socio = socio;
        this.prestado = prestado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDia_prestamo() {
        return dia_prestamo;
    }

    public void setDia_prestamo(Date dia_prestamo) {
        this.dia_prestamo = dia_prestamo;
    }

    public Date getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Date devolucion) {
        this.devolucion = devolucion;
    }

    public Persona getSocio() {
        return socio;
    }

    public void setSocio(Persona socio) {
        this.socio = socio;
    }

    public ArrayList<Libro> getPrestado() {
        return prestado;
    }

    public void setPrestado(ArrayList<Libro> prestado) {
        this.prestado = prestado;
    }
    
    
}
