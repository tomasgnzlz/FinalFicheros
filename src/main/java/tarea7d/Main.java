/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author tomas
 */
public class Main {

    public static void main(String[] args) throws JAXBException {
        // A
        List<App> listaApps = new ArrayList<>();
        listaApps = listaAplicaciones();
        listaApps.forEach(System.out::println);

        // B
        System.out.println("\n\n");
        List<String> listaString = new ArrayList<>();
        listaString = listaString(listaApps);
        // creo el directorio ./appscsv y dentro guardo la lista de Apps
        String rutaDirectorio = "./appscsv";
        SistemasFicherosVico.crearDirectorio(rutaDirectorio);
        LecturaEscritura.escribirFicheroListas(rutaDirectorio + "/aplicacionestxt.csv", listaString);

        // C 
        rutaDirectorio = "./appscsv2";
        SistemasFicherosVico.crearDirectorio(rutaDirectorio);
        ficherosDatosAplicaciones(listaApps, rutaDirectorio);

        // D
        CatalogoApps catalogo = new CatalogoApps();
        catalogo.setListaApps(listaApps);
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
        serializador.marshal(catalogo, new File("catalogo.xml"));
        // E (Se hace en clase el jueves)
        // F
        rutaDirectorio = "./copias";
        SistemasFicherosVico.crearDirectorio(rutaDirectorio);
        SistemasFicherosVico.copiarFicheros("./appscsv/aplicacionestxt.csv", rutaDirectorio + "/copiasFicheroApplicaciones.csv");

        // G
        // no es lo mismo que hago en directorio de appscsv??????
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

    // Método que pasa de una lista de APPS a una lista de String
    public static List<String> listaString(List<App> listaAux) {
        List<String> listaString = new ArrayList<>();
        for (App app : listaAux) {
            listaString.add(app.toString());
        }
        return listaString;
    }

    // Método que escribe un fichero con el nombre de cada aplicacion, y dentro
    // de ese fichero csv se esciben los datos de esa aplicacion
    public static void ficherosDatosAplicaciones(List<App> listaApps, String rutaFichero) {
        for (App appAux : listaApps) {
            LecturaEscritura.escribirFicheroSencillo(rutaFichero + "/" + appAux.getNombre() + ".csv", appAux.toString());
        }
    }
}
