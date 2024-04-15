/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import tarea7e.CatalogoApps;

/**
 *
 * @author tomas
 */
public class XML {

    /**
     * *****************************ESCRITURA_XML*****************************
     */
    //Método que genera el fichero xml con la lista de objetos que le pase
    public static <T> void generarFicheroXML(String nombreFicheroXML, T objeto) throws JAXBException {
        // Crea el contexto JAXB. Se encarga de definir los objetos 
        // que vamos a guardar. En nuestro caso sólo el tipo CatalogoMuebles
        JAXBContext contexto = JAXBContext.newInstance(objeto.getClass());

        // El contexto JAXB permite crear un objeto Marshaller, que sirve para
        // generar la estructura del fichero XML 
        // El proceso de pasar objetos Java (CatalogoMuebles) a ficheros XML 
        // se conoce como "marshalling" o "serialización"
        Marshaller serializador = contexto.createMarshaller();

        // Especificamos que la propiedad del formato de salida
        // del serializador sea true, lo que implica que el formato se 
        // realiza con indentación y saltos de línea
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Llamando al método de serialización marshal (sobrecargado) se pueden
        // serializar objetos java en formato XML y volcarlos donde necesitemos:
        // consola, ficheros. El proceso consiste en que el contexto es el 
        // encargo de leer los objetos java, pasarlos al serializador y éste 
        // crear la salida de serialización
        // Serialización y salida por consola
        serializador.marshal(objeto, System.out);
        // Volcado al fichero xml
        serializador.marshal(objeto, new File(nombreFicheroXML));
    }

    /**
     * *****************************LECTURA_XML*****************************
     */
    // Método que realiza una lectura del fichero XML y muestra todos los datos.
    // Le paso el nombre dle fichero xml  a leer
    // Le paso el objeto catalogo, porque en los xml tengo que crear una clase 
    // que contenga una lista de objetos del objeto que quiero leer, devuelvo 
    // ese objeto y ya con el obhjeto puedo mostrar la lista o hacer lo que se pida
    public static <T> T lecturaFicheroXML(String nombreFichero, Class<T> objetoCatalogo) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(objetoCatalogo);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();
        return (T) um.unmarshal(new File(nombreFichero));
    }

}
