 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7e;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author tomas
 */
public class Main {

    public static void main(String[] args) throws JAXBException, IOException {
        String rutaDirectorio;
        List<App> listaApps = listaAplicaciones();
        listaApps.forEach(System.out::println);

        // A
        CatalogoApps catalogo = new CatalogoApps();
        catalogo.setListaApps(listaApps);
        generarFicheroXML(catalogo, "catalogo.xml");

        // B
        rutaDirectorio = "./";
        generarFicherosJSON(rutaDirectorio, listaApps);

        // C
        // Lo comento, que sino se generan mas de 50, porque el directorio existe y tiene valores previos
//        rutaDirectorio = "./aplicacionesJSON";
//        generarJSONPorCadaApp(rutaDirectorio, listaApps);
        // D
        String nombreFichero = "catalogo.xml";
        lecturaFicheroXML(nombreFichero);

        // E
        nombreFichero = "catalogoApps.json";
        lecturaFicheroJSON(nombreFichero);

        // F
        rutaDirectorio = "./aplicacionesJSON";
        System.out.println("\nLISTADO DEL DIRECTORIO: " + rutaDirectorio + "\n");
        SistemasFicheros.listarDirectorio(rutaDirectorio);

        // G
        lecturaFicheroConcreto();
        
        // H
//        SistemasFicheros.borrarElemento(rutaDirectorio+ "/app50o.json");
//        System.out.println("\nLISTADO DEL DIRECTORIO: " + rutaDirectorio + "\n");
//        SistemasFicheros.listarDirectorio(rutaDirectorio);
    }

    // Método que crea 50 aplicaciones usando el constructor por defecto
    public static List<App> listaAplicaciones() {
        List<App> lista = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            App appAux = new App();
            lista.add(appAux);
        }
        return lista;
    }

    //Método que genera el fichero xml con la lista de Aplicaciones
    public static void generarFicheroXML(CatalogoApps catalogo, String nombreFicheroXML) throws JAXBException {
        // Crea el contexto JAXB. Se encarga de definir los objetos 
        // que vamos a guardar. En nuestro caso sólo el tipo CatalogoMuebles
        JAXBContext contexto = JAXBContext.newInstance(CatalogoApps.class);

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
        serializador.marshal(catalogo, System.out);

        // Volcado al fichero xml
        serializador.marshal(catalogo, new File(nombreFicheroXML));
    }

    // Método para generar ficheros json
    public static void generarFicherosJSON(String rutaDirectorio, List<App> listaApps) throws IOException {

        SistemasFicheros.crearDirectorio(rutaDirectorio);
        ObjectMapper mapeador = new ObjectMapper();

        // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON el catálogo de muebles
        mapeador.writeValue(new File(rutaDirectorio + "/catalogoApps.json"), listaApps);
    }

    // Método que por cada Applicación cree un fichero con el nombre de esa app 
    // y el contenido son los datos de las aplicaciones
    public static void generarJSONPorCadaApp(String rutaDirectorio, List<App> listaApps) throws IOException {
        // Creo el directorio en el que iran los JSON de cada App
        SistemasFicheros.crearDirectorio(rutaDirectorio);

        // Creo los JSON de cada app
        for (App a : listaApps) {
            ObjectMapper mapeador = new ObjectMapper();
            // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
            mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

            String nombreJSON = a.getNombre();

            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(rutaDirectorio + "/" + nombreJSON + ".json"), a);
        }
    }

    // Método que realiza una lectura del fichero XML y muestra todos los datos.
    public static void lecturaFicheroXML(String nombreFichero) throws JAXBException {

        CatalogoApps catalogoAux = new CatalogoApps();

        JAXBContext contexto = JAXBContext.newInstance(CatalogoApps.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();
        catalogoAux = (CatalogoApps) um.unmarshal(new File(nombreFichero));

        List<App> listaApps = catalogoAux.getListaApps();
        listaApps.forEach(System.out::println);
    }

    // Método que realiza una lectura del fichero JSON y muestra todos los datos.
    public static void lecturaFicheroJSON(String nombreFichero) throws IOException {
        List<App> listaAplicaciones = new ArrayList<>();
        ObjectMapper mapeador = new ObjectMapper();

        listaAplicaciones = mapeador.readValue(new File(nombreFichero),
                mapeador.getTypeFactory().constructCollectionType(List.class, App.class));
        System.out.println("---- Lista de Apps ----");
        for (App app : listaAplicaciones) {
            System.out.println(app);
        }
    }

    // Pregunta al usuario el nombre del fichero json (de los anteriores) que quiere leer. 
    // Una vez leído muestra los datos de esta app por consola.
    public static void lecturaFicheroConcreto() throws IOException {
        Scanner teclado = new Scanner(System.in);
        String id = "";
        System.out.println("************************************************************"
                + "\nIntrooduce el fichero que quieres leer");
        id = teclado.nextLine();

        // Se puede controlar excepciones
        System.out.println("\n********* LECTURA DEL FICHERO: " + id);
        ObjectMapper mapeador = new ObjectMapper();

        App aplicacion = mapeador.readValue(new File("./aplicacionesJSON/" + id), App.class);
        System.out.println(aplicacion);
        SistemasFicheros.borrarElemento("./aplicacionesJSON/" + id);
    }
}
