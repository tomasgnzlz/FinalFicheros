/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package tarea7f;

import com.mycompany.tarea7ctomas.LecturaEscritura;
import com.mycompany.tarea7ctomas.Persona;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tomas
 */
public class Tarea7FTomas {

    public static void main(String[] args) {
        List<String> listaString = new ArrayList<>();
        listaString = LecturaEscritura.leerFichero("personas.csv");
        listaString.forEach(System.out::println);
        System.out.println("\n");

        // Creo la lista de personas en la que guardar los datos del método
        List<Persona> listaPersonas = obtenerListaPersonas(listaString);
        listaPersonas.forEach(System.out::println);

        // 
        System.out.println("\n\n  EJERCICIO 1");
        List<Persona> listaViejosClasico = listasViejosClasicos(listaPersonas);
        listaViejosClasico.forEach(System.out::println);

        System.out.println("\n\n");
        Stream<Persona> listaViejosStream = listaViejosStream(listaPersonas);
        listaViejosStream.forEach(System.out::println);

        // B
        System.out.println("\n\n  EJERCICIO 2");
        conjuntoEmailsJubiladosNBClasico(listaPersonas);
        System.out.println("\n\n");
        conjuntoEmailsJubiladosNBStream(listaPersonas);

        // C
        System.out.println("\n\n  EJERCICIO 3");
        numCiudadesClasico(listaPersonas);
        System.out.println("\n");
        numCiudadesStream(listaPersonas);

        // D
        System.out.println("\n\n  EJERCICIO 4");
        busquedaZondraClasico(listaPersonas);
        System.out.println("\n");
        busquedaZondraStream(listaPersonas);

        // E
        System.out.println("\n\n  EJERCICIO 5");
        EsteponaClasico(listaPersonas);
        System.out.println("\n");
        EsteponaStream(listaPersonas);

        // F
        System.out.println("\n\n  EJERCICIO 6");
        apellidoFinalClasico(listaPersonas);
        System.out.println("\n");
        apellidoFinalStream(listaPersonas);

        // G
        System.out.println("\n\n  EJERCICIO 7");
        listaEdadesClasico(listaPersonas);
        System.out.println("\n");
        listaEdadesStream(listaPersonas);

        // H
        System.out.println("\n\n  EJERCICIO 8");
        sumaListaEdadesClasico(listaPersonas);
        System.out.println("\n");
        sumaListaEdadesStream(listaPersonas);

        // I
        System.out.println("\n\n  EJERCICIO 9");
        mediaEdadesClasico(listaPersonas);
        System.out.println("\n");
        mediaEdadesStream(listaPersonas);

        // J
        System.out.println("\n\n  EJERCICIO 10");
        System.out.println("\n");

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

    // Obtener una lista de personas nacidas en 1985, ordenadas por su email.
    public static List<Persona> listasViejosClasicos(List<Persona> listaPersonas) {
        List<Persona> listasViejos = new ArrayList<>();
        for (Persona pAux : listaPersonas) {
            if (pAux.getFechaNacimiento().getYear() == 1985) {
                listasViejos.add(pAux);
            }
        }
        listasViejos = OrdenarPorEmail(listasViejos);
        return listasViejos;
    }

    // Método que ordena una lista por email
    public static List<Persona> OrdenarPorEmail(List<Persona> listaViejos) {
        Comparator<Persona> ordenarPorEmail = (a, b) -> a.getEmail().compareToIgnoreCase(b.getEmail());
        Collections.sort(listaViejos, ordenarPorEmail);
        return listaViejos;
    }

    public static Stream<Persona> listaViejosStream(List<Persona> listaPersonas) {
        return listaPersonas.stream()
                .filter(p -> p.getFechaNacimiento().getYear() == 1985)
                .sorted((a, b) -> a.getEmail().compareToIgnoreCase(b.getEmail()));
    }

    // Obtener un set de correos electrónicos, ordenados alfabéticamente, 
    // de aquellas personas cuyo género es "non-binary" y estén jubiladas.
    public static void conjuntoEmailsJubiladosNBClasico(List<Persona> listaPersonas) {
        Set<Persona> conjuntoEmail = new HashSet<>();
        for (Persona pAux : listaPersonas) {
            if (pAux.isJubilado() && pAux.getGenero() == "Non-binary") {
                conjuntoEmail.add(pAux);
            }
        }

        for (Persona persona : conjuntoEmail) {
            System.out.println(persona);
        }
    }

    public static void conjuntoEmailsJubiladosNBStream(List<Persona> listaPersonas) {
        Stream<Persona> stream = listaPersonas.stream().filter(a -> a.getGenero() == "Non-binary" && a.isJubilado());
        stream.forEach(System.out::println);
    }

    // Obtener el número de ciudades diferentes.
    public static void numCiudadesClasico(List<Persona> listaPersonas) {
        Set<String> conjunto = new HashSet<>();
        for (Persona c : listaPersonas) {
            conjunto.add(c.getCiudad());
        }

        System.out.println("Numero ciudades: " + conjunto.size());
    }

    public static void numCiudadesStream(List<Persona> listaPersonas) {
        List<String> array = new ArrayList<>();

        for (int i = 0; i < listaPersonas.size(); i++) {
            array.add(listaPersonas.get(i).getCiudad());
        }

        Stream<String> ciudadesDistintas = array.stream().distinct();
        System.out.println("Numero ciudades: " + ciudadesDistintas.count());
    }

    // Comprobar si alguna persona se llama "Zondra".
    public static void busquedaZondraClasico(List<Persona> listaPersonas) {
        boolean existe = false;
        for (Persona listaPersona : listaPersonas) {
            if (listaPersona.getNombre().equals("Zondra")) {
                existe = true;
            }
        }
        System.out.println("Existe:" + existe);
    }

    public static void busquedaZondraStream(List<Persona> listaPersonas) {
        Boolean stream = listaPersonas.stream().anyMatch(a -> a.getNombre().equals("Zondra"));
        System.out.println("Existe:" + stream);
    }

    // Comprobar si ninguna persona vive en "Estepona".
    public static void EsteponaClasico(List<Persona> listaPersonas) {
        boolean existe = false;
        for (Persona listaPersona : listaPersonas) {
            if (listaPersona.getNombre().equalsIgnoreCase("Estepona")) {
                existe = true;
            }
        }
        System.out.println("Alguien vive en estepona? " + existe);
    }

    public static void EsteponaStream(List<Persona> listaPersonas) {
        Boolean stream = listaPersonas.stream().anyMatch(a -> a.getCiudad().equalsIgnoreCase("Estepona"));
        System.out.println("Alguien vive en estepona? " + stream);
    }

    // Saber cuantas personas tienen en su apellido la terminación "cía".
    public static void apellidoFinalClasico(List<Persona> listaPersonas) {
        List<Persona> listaApellidosfinales = new ArrayList<>();
        for (Persona a : listaPersonas) {
            if (a.getApellido().endsWith("cía")) {
                listaApellidosfinales.add(a);
            }
        }
        System.out.println(listaApellidosfinales.size());
    }

    public static void apellidoFinalStream(List<Persona> listaPersonas) {
        int num = (int) listaPersonas.stream().filter(persona -> persona.getApellido().endsWith("cía")).count();
        System.out.println(num);

    }

    // Obtener una lista con todas las edades de cada persona.
    public static void listaEdadesClasico(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        for (Persona p : listaPersonas) {
            listaEdades.add(p.edadPersona());
        }

        listaEdades.forEach(System.out::println);
    }

    public static void listaEdadesStream(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        listaEdades = listaPersonas.stream().map(Persona::edadPersona).collect(Collectors.toList());
        listaEdades.forEach(System.out::println);
    }

    // Obtener la suma de todas las edades de las personas de la lista.
    public static void sumaListaEdadesClasico(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        for (Persona p : listaPersonas) {
            listaEdades.add(p.edadPersona());
        }
        int contador = 0;
        for (Integer e : listaEdades) {
            contador = contador + e;
        }

        System.out.println("La suma de todas las edades es : " + contador);
    }

    public static void sumaListaEdadesStream(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        int sumaEdades = listaPersonas.stream().mapToInt(Persona::edadPersona).sum();
        System.out.println("La suma de todas las edades es: " + sumaEdades);
    }

    // Obtener la media de edad de las personas de la lista.
    public static void mediaEdadesClasico(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        for (Persona p : listaPersonas) {
            listaEdades.add(p.edadPersona());
        }
        int contador = 0;
        for (Integer e : listaEdades) {
            contador = contador + e;
        }
        System.out.println("La media de todas las edades es : " + contador / listaEdades.size());
    }

    public static void mediaEdadesStream(List<Persona> listaPersonas) {
        List<Integer> listaEdades = new ArrayList<>();
        int sumaEdades = listaPersonas.stream().mapToInt(Persona::edadPersona).sum();
        double mediaEdades = (double) sumaEdades / listaPersonas.size();
        System.out.println("La media de todas las edades es: " + mediaEdades);
    }

    // Obtener en un String todos los nombres de las personas concatenados.
    public static void nombresConcatClasico(List<Persona> listaPersonas) {
        String nombreFinal = "";
        for (Persona p : listaPersonas) {
            nombreFinal = nombreFinal.concat(p.getNombre());
        }
    }
    
    public static void nombresConcatStream(List<Persona> listaPersonas){
        
    }

}
