/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen2;

/**
 *
 * @author DELL
 */

public class HashTable {
    private Entry cabeza;

    // nueva entrada a tabla
    public void agregar(String nombreUsuario, long posicion) {
        Entry nuevaEntrada = new Entry(nombreUsuario, posicion);
        if (cabeza == null) {
            cabeza = nuevaEntrada;
        } else {
            Entry actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaEntrada;
        }
    }

     // Elimina una entrada de tabla 
    public void eliminar(String nombreUsuario) {
        if (cabeza == null) return;

        if (cabeza.nombreUsuario.equals(nombreUsuario)) {
            cabeza = cabeza.siguiente;
            return;
        }

        Entry actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.nombreUsuario.equals(nombreUsuario)) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }

     // Busca una entrada en tabla
    public long buscar(String nombreUsuario) {
        Entry actual = cabeza;
        while (actual != null) {
            if (actual.nombreUsuario.equals(nombreUsuario)) {
                return actual.posicion;
            }
            actual = actual.siguiente;
        }
        return -1;
    }
}