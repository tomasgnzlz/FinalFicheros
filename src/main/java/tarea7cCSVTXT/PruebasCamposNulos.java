/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7cCSVTXT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas
 */
public class PruebasCamposNulos {

    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista = LecturaEscritura.leerFichero("personasNulos.csv");
        lista.forEach(System.out::println);
        System.out.println("\nLISTA DE OBJETOS PERSONA");
        List<Persona> listaPersonas = obtenerListaPersonasCamposNulos(lista);
        listaPersonas.forEach(System.out::println);

    }

    public static List<Persona> obtenerListaPersonasCamposNulos(List<String> listaLineas) {
        List<Persona> listaPersonas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (String l : listaLineas) {
            // Separo la lineas por las comas que tengan
            String[] array = l.split(",");

            // COMO GUARDO LOS CAMPOS EN STRING SI ESA POS DE ARRAY ESTÁ VACÍA 
            // PONGO ESE CAMPO A NULL.
            String id = (array[0].isEmpty()) ? null : array[0];
            String nombre = (array[1].isEmpty()) ? null : array[1];
            String apellido = (array[2].isEmpty()) ? null : array[2];
            String email = (array[3].isEmpty()) ? null : array[3];
            String genero = (array[4].isEmpty()) ? null : array[4];
            LocalDate fechaNacimiento = (array[5].isEmpty()) ? null : LocalDate.parse(array[5], formatter);
            boolean jubilado = (array[6].isEmpty()) ? null : Boolean.valueOf(array[6]);
            String ciudad = (array[7].isEmpty()) ? null : array[7];

            // Creo el objeto persona a partir de los parametros obtenids.
            Persona pAux = new Persona(id, nombre, apellido, email, genero, fechaNacimiento, jubilado, ciudad);
            listaPersonas.add(pAux);
        }
        return listaPersonas;
    }
}
