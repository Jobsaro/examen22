/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen2;

/**
 *
 * @author DELL
 */

public class Entry {
    String nombreUsuario;
    long posicion;
    Entry siguiente;

    // Constructor para crear una nueva entrada
    public Entry(String nombreUsuario, long posicion) {
        this.nombreUsuario = nombreUsuario;
        this.posicion = posicion;
        this.siguiente = null;
    }
}