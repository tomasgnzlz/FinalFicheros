/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7e;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author tomas
 */
public class App {

    private int codigo;
    private String nombre;
    private String descripcion;
    private double tamanioKB;
    private int numDescargas;
    private static int contador = 1;

    // Constructores
    public App() {
        this.codigo = contador++;
        this.nombre = generarNombre(this.codigo);
        this.descripcion = descripcionAleatoria();
        this.tamanioKB = new Random().doubles(1, 100.0, 1024.0).sum();
        this.numDescargas = new Random().ints(1, 0, 5000).sum();
    }

    public App(int codigo, String nombre, String descripcion, double tamanioKB, int numDescargas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanioKB = tamanioKB;
        this.numDescargas = numDescargas;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTamañoKB() {
        return tamanioKB;
    }

    public void setTamañoKB(double tamañoKB) {
        this.tamanioKB = tamañoKB;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    // toString
    @Override
    public String toString() {
        return codigo + "," + nombre + "," + descripcion + "," + tamanioKB + "," + numDescargas;
    }
    
    public String toString2() {
        return codigo + ";" + nombre + ";" + descripcion + ";" + tamanioKB + ";" + numDescargas;
    }

    // Método que genera un nombre de entre 10;
    public static String generarNombre(int codigo) {
        return "app" + codigo + new RandomStringUtils().randomAlphabetic(1).toLowerCase();
    }

    // Método que escoge una descripcion de entre 10 Random
    public static String descripcionAleatoria() {
        String array[] = {"tomas1", "Tomas2", "tomas3", "Tomas4", "tomas5", "Tomas6", "tomas7", "Tomas8", "tomas9", "Tomas10"};
        return array[new Random().nextInt(array.length - 1)];
    }
}
