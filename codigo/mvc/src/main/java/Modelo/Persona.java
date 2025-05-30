/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ASUS
 */
public class Persona {
    public int dni;
    public String nombre;
    
    public Persona(){}
    public Persona(int dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
    }

    public void mostrar(){
        System.out.println(nombre + dni );
    }

    public int getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }
}
