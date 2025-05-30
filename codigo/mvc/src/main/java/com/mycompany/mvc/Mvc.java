/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mvc;

import Controlador.Controlador;
import Modelo.Libro;
import Modelo.Modelo;
import Modelo.Persona;
import Modelo.Prestamo;
import Vista.Vista;

/**
 *
 * @author ASUS
 */
public class Mvc {

    public static void main(String[] args) {
        Modelo mod = new Modelo();
        Vista view = new Vista();
        Persona persona = new Persona();
        Prestamo prestamo = new Prestamo();
        Libro libro = new Libro();
        
        Controlador ctrl = new Controlador(persona,prestamo,libro,view);
        ctrl.iniciar();
        view.setVisible(true);
       
    }
}
