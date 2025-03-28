/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen2;

/**
 *
 * @author DELL
 */

public enum Trophy {
    PLATINO(5), ORO(3), PLATA(2), BRONCE(1);

    public final int puntos;

    // Constructor para definir los puntos de trofeo
    Trophy(int puntos) {
        this.puntos = puntos;
    }
}