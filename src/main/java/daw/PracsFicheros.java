/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package daw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author tomas
 * PRUEBA DE LOS METODSOS JSON Y XML
 */
public class PracsFicheros {
    
    public static void main(String[] args) throws IOException, JAXBException {
        
        // LECTURA DE UN FICHERO JSON(LISTA DE DATOS)
        String nombreFicheroLeer = "precipitacionesBadajoz.json";
        List<ClasePojo> lista = new ArrayList<>();
        lista = JSON.lecturaConjuntoJSON(nombreFicheroLeer, ClasePojo.class, lista);
        lista.forEach(System.out::println);
        
        // ESCRITURA DE UN FICHERO JSON(LISTA DE DATOS)
//        JSON.generarFicherosJSON("prueba.json", lista);

        // ESCRITURA DE UN FICHERO XML
        System.out.println("\n\n");
        nombreFicheroLeer= "pruebaDELXML.xml";
        CatalogoPojo catalogo = new CatalogoPojo();
        catalogo.setLista(lista);
        XML.generarFicheroXML(nombreFicheroLeer,catalogo);
        
//        
        
        
        
    }
}
