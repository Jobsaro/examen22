/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen2;

/**
 *
 * @author DELL
 */

import java.io.*;
import java.util.*;

public class PSNUser {
    private RandomAccessFile archivoPSN;
    private HashTable usuarios;

    // para inicializar el archivo y la tabla 
    public PSNUser(String rutaArchivo) throws IOException {
        this.usuarios = new HashTable();
        this.archivoPSN = new RandomAccessFile(rutaArchivo, "rw");
        recargarTablaHash();
    }

    // Recargar los datos del archivo en tabla 
    private void recargarTablaHash() throws IOException {
        archivoPSN.seek(0);
        String linea;
        while ((linea = archivoPSN.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length > 1 && datos[2].equals("activo")) {
                usuarios.agregar(datos[0], Long.parseLong(datos[1]));
            }
        }
    }

    // Agregar un usuario
    public void agregarUsuario(String nombreUsuario) throws IOException {
        if (usuarios.buscar(nombreUsuario) == -1) {
            long posicion = archivoPSN.length();
            archivoPSN.seek(posicion);
            archivoPSN.writeBytes(nombreUsuario + "," + posicion + ",activo,0,0\n");
            usuarios.agregar(nombreUsuario, posicion);
        }
    }

    // Desactivar usuario
    public void desactivarUsuario(String nombreUsuario) throws IOException {
        long posicion = usuarios.buscar(nombreUsuario);
        if (posicion != -1) {
            archivoPSN.seek(posicion);
            String linea = archivoPSN.readLine();
            String[] datos = linea.split(",");
            datos[2] = "inactivo";
            archivoPSN.seek(posicion);
            archivoPSN.writeBytes(String.join(",", datos) + "\n");
            usuarios.eliminar(nombreUsuario);
        }
    }

    // Agregar un trofeo a un usuario
    public void agregarTrofeoA(String nombreUsuario, String juegoTrofeo, String nombreTrofeo, Trophy tipo) throws IOException {
        long posicion = usuarios.buscar(nombreUsuario);
        if (posicion != -1) {
            archivoPSN.seek(posicion);
            String linea = archivoPSN.readLine();
            String[] datos = linea.split(",");
            int trofeos = Integer.parseInt(datos[4]) + 1;
            int puntos = Integer.parseInt(datos[3]) + tipo.puntos;
            datos[3] = String.valueOf(puntos);
            datos[4] = String.valueOf(trofeos);
            archivoPSN.seek(posicion);
            archivoPSN.writeBytes(String.join(",", datos) + "\n");

            archivoPSN.seek(archivoPSN.length());
            archivoPSN.writeBytes(nombreUsuario + "," + new Date() + "," + tipo + "," + juegoTrofeo + "," + nombreTrofeo + "\n");
        }
    }

    // Mostrar informacion de usuario
    public void informacionUsuario(String nombreUsuario) throws IOException {
        long posicion = usuarios.buscar(nombreUsuario);
        if (posicion != -1) {
            archivoPSN.seek(posicion);
            String linea = archivoPSN.readLine();
            System.out.println(linea);
            archivoPSN.seek(0);
            while ((linea = archivoPSN.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(nombreUsuario) && datos.length > 1) {
                    System.out.println(datos[1] + " - " + datos[2] + " - " + datos[3] + " - " + datos[4]);
                }
            }
        }
    }

    // Obtener informacion de usuario
    public String getInformacionUsuario(String nombreUsuario) throws IOException {
        StringBuilder informacion = new StringBuilder();
        long posicion = usuarios.buscar(nombreUsuario);
        if (posicion != -1) {
            archivoPSN.seek(posicion);
            String linea = archivoPSN.readLine();
            informacion.append(linea).append("\n");
            archivoPSN.seek(0);
            while ((linea = archivoPSN.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(nombreUsuario) && datos.length > 1) {
                    informacion.append(datos[1]).append(" - ").append(datos[2]).append(" - ").append(datos[3]).append(" - ").append(datos[4]).append("\n");
                }
            }
        }
        return informacion.toString();
    }

    // Verifica si un usuario ya existe
    public boolean existeUsuario(String nombreUsuario) throws IOException {
        return usuarios.buscar(nombreUsuario) != -1;
    }
}