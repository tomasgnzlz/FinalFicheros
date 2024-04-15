/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package tarea7cCSVTXT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author tomas
 */
public class Tarea7CTomas {

    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista = LecturaEscritura.leerFichero("personas.csv");
        lista.forEach(System.out::println);
        System.out.println("\n");
        // Creo la lista de personas en la que guardar los datos del método

        List<Persona> listaPersonas = obtenerListaPersonas(lista);
        listaPersonas.forEach(System.out::println);
        System.out.println("\n");

        //
        // Creo un conjunto Set en el que obtener los diff generos que se encuentran
        System.out.println("**********GENEROS PERSONAS**********");
        Set<String> diffGeneros = new TreeSet<>();
        diffGeneros = generosPersonas(listaPersonas);
        diffGeneros.forEach(System.out::println);

        //
        // Paso el set a una lista de String para poder usar el metodo de escritura
        List<String> generos = new ArrayList<>(diffGeneros);
        LecturaEscritura.escribirFichero("generos.txt", generos);

        //
        // Creo un map en el que poner el genero y la repeticion del mismo en el csv
        System.out.println("**********REPETICIONES GENEROS**********");
        Map<String, Integer> map = new HashMap<>();
        map = repsGeneros(listaPersonas);
        mostrarMap(map);

        //
        // Paso el Map a una lista de String para poder usar el metodo de escritura
        List<String> contadorGeneros = new ArrayList<>();
        contadorGeneros = ListaMap(map);
        LecturaEscritura.escribirFichero("contadorGeneros.csv", contadorGeneros);

    }

    // Método que devuelve lista de personas a partir de la lista de String
    public static List<Persona> obtenerListaPersonas(List<String> listaLineas) {
        List<Persona> listaPersonas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (String l : listaLineas) {
            // Separo la lineas por las comas que tengan
            String[] array = l.split(",");

            // Guardo lada elemento del array en una variable
            String id = array[0];
            String nombre = array[1];
            String apellido = array[2];
            String email = array[3];
            String genero = array[4];
            LocalDate fechaNacimiento = LocalDate.parse(array[5], formatter);
            boolean jubilado = Boolean.valueOf(array[6]);
            String ciudad = array[7];

            // Creo el objeto persona a partir de los parametros obtenids.
            Persona pAux = new Persona(id, nombre, apellido, email, genero, fechaNacimiento, jubilado, ciudad);
            listaPersonas.add(pAux);
        }
        return listaPersonas;
    }

    // Métood que a partir de la lista de personas obtiene un set con los diff generos
    public static Set<String> generosPersonas(List<Persona> lista) {
        Set<String> diffGeneros = new TreeSet<>();
        for (Persona p : lista) {
            diffGeneros.add(p.getGenero());
        }
        return diffGeneros;
    }

    // Método que a partir de la lista de personas obtiene un map con los generos y la repeticion del mismo
    public static Map<String, Integer> repsGeneros(List<Persona> lista) {
        Map<String, Integer> map = new HashMap<>();
        int contador = 0;
        for (Persona persona : lista) {
            if (map.containsKey(persona.getGenero())) {
                // si ese genero ya esta en el map, solo se incrementa el contador
                // cogo el value de la key, que es el genero, y le sumo 1. 
                contador = map.get(persona.getGenero());
                map.put(persona.getGenero(), contador + 1);
            } else {
                // si ese genero no esta en el map, se lo añade y se pone como value 1,
                // porque es la primera vez que se añade ese genero al map
                map.put(persona.getGenero(), 1);
            }
        }
        return map;
    }

    // Método que a partir de un Map crea una lista de String
    public static List<String> ListaMap(Map<String, Integer> map) {
        String texto = "";
        List<String> lista = new ArrayList<>();
        for (Map.Entry<String, Integer> p : map.entrySet()) {
            texto = (p.getKey() + "," + p.getValue());
            lista.add(texto);
        }
        return lista;

    }

    // Método para mostrar el Map
    public static void mostrarMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> p : map.entrySet()) {
            System.out.println(p.getKey() + " --> " + p.getValue());
        }
    }

    //*********************OBTENER LISTAS PERSONAS CON CAMPOS VACIOS
    public static List<Persona> obtenerListaPersonasCamposNulos(List<String> listaLineas) {
        List<Persona> listaPersonas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (String l : listaLineas) {
            // Separo la lineas por las comas que tengan
            String[] array = l.split(",");

            String id;
            if (array[0] == null) {
                id = null;
            } else {
                id = array[0];
            }

            String nombre = array[1];
            String apellido = array[2];
            String email = array[3];
            String genero = array[4];
            LocalDate fechaNacimiento = LocalDate.parse(array[5], formatter);
            boolean jubilado = Boolean.valueOf(array[6]);
            String ciudad = array[7];

            // Creo el objeto persona a partir de los parametros obtenids.
            Persona pAux = new Persona(id, nombre, apellido, email, genero, fechaNacimiento, jubilado, ciudad);
            listaPersonas.add(pAux);
        }
        return listaPersonas;
    }
}
