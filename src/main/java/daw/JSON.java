/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tomas
 */
public class JSON {

    /**
     ******************************LECTURA_JSON******************************
     */
    // Método que realiza una lectura del fichero JSON y muestra todos los datos.
    // El nombre del fichero a leer
    // La clase Pojo de los objetos a leer
    // La lista de objetosPOJO para guardar los datos que lee del fichero json
    public static <T> List<T> lecturaConjuntoJSON(String nombreFicheroLeer, Class<T> clase, List<T> lista) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.registerModule(new JavaTimeModule());

        lista = mapeador.readValue(new File(nombreFicheroLeer),
                mapeador.getTypeFactory().constructCollectionType(List.class, clase));

        return lista;
    }

    // Método que lee un un fichero json, pero solo uno, no muchos, sino uso el 
    // metodo de arriba que lee conjunto de datos
    // El nombre del fichero a leer
    // el objeto.class que quieras que devuelva
    public static <T> T lecturaFicheroConcreto(String nombreFicheroLeer, Class<T> clase) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.registerModule(new JavaTimeModule());

        return mapeador.readValue(new File("./aplicacionesJSON/" + nombreFicheroLeer), clase);
    }

    /**
     * ****************************ESCRITURA_JSON*****************************
     */
    // Método para generar ficheros json a partir de listas de objetos
    // El nombre del fichero a crear(si no lo meto en u directorio se crea en ./)
    // La lista de objetos de la que quiero crear un json
    public static <T> void generarFicherosJSON(String nombreJson, List<T> lista) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.registerModule(new JavaTimeModule());

        // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON la lista de objetos que se le pasan
        mapeador.writeValue(new File(nombreJson), lista);
    }

    // Método para generar ficheros json a partir de  1 objetos
    // El nombre del fichero a crear(si no lo meto en u directorio se crea en ./)
    // El objeto del que quiero crear un json
    public static <T> void generarFicheroJSON(String nombreJson, Object objetoCrearJson) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.registerModule(new JavaTimeModule());

        // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON la lista de objetos que se le pasan
        mapeador.writeValue(new File(nombreJson), objetoCrearJson);
    }

}
