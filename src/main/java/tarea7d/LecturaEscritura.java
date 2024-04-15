/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7d;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tomas
 */
public class LecturaEscritura {

    // Método para leer un fichero csv y guardarlo en una lista de String
    public static List<String> leerFichero(String nombreFichero) {
        List<String> lista = new ArrayList<>();
        try {
            lista = Files.readAllLines(Paths.get(nombreFichero));
            if (!lista.isEmpty()) {
                lista.remove(0);
                //HAgo que no lea la primera linea, que sino al pasar los datos
                // al  objeto vehiculo, el año no es un String. 
            }
        } catch (IOException ioe) {
            System.out.println("Error accediendo a fichero: " + nombreFichero);
        }
        return lista;
    }

    // Método que escribe en un fichero el contenido una lista de String
    // Le paso la lista de String que escribir, y el nombre al fichero a crear con esos datos. 
    public static void escribirFicheroListas(String nombreFichero, List<String> diffGeneros) {
        try {
            Files.write(Paths.get(nombreFichero), diffGeneros);
        } catch (IOException e) {
            System.out.println("ERROR AL ESCRIBRI EN EL FICHERO");
        }
    }

    public static void escribirFicheroSencillo(String nombreFichero, String texto) {
        try {
            Files.write(Paths.get(nombreFichero), texto.getBytes());
        } catch (IOException e) {
            System.out.println("ERROR AL ESCRIBRI EN EL FICHERO");
        }
    }

}
