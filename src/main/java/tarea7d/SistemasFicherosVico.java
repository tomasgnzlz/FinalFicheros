/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea7d;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
// La clase final Files contiene métodos estáticos para trabajar con 
// ficheros y directorios
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
// Path es una interfaz que representa objetos del sistema de ficheros
// como directorios, ficheros, enlaces (absolutos, relativos).
import java.nio.file.Path;
// La clase final Paths sólo contine métodos estáticos que devuelven
// objetos tipo Path a partir de String o URI (Uniform Resource Identifier)
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas
 */
public class SistemasFicherosVico {

    public static void main(String[] args) {

        // Creación de un directorio en el directorio actual del proyecto
        crearDirectorio("./files");
        // Creación de un directorio en el directorio padre del proyecto
        crearDirectorio("../directorioPadre");
        // Creación de un directorio en una ruta absoluta
        crearDirectorio("/home/usuario/rutaAbsoluta");
        // Creación de un directorio en una ruta sin permisos
        crearDirectorio("/sinPermisos2");
        // Creación de todos los directorios especificados en la ruta
        crearDirectorios("./directorioActual/uno/dos/tres");

        // Creación de un fichero vacío en un directorio del proyecto
        crearFichero("./files/ficheroVacio.txt");

        // ¿Existe el directorio "../directorioPadre"?
        System.out.println("¿Existe ../directorioPadre ? "
                + Files.exists(Paths.get("../directorioPadre")));

        borrarElemento("../directorioPadre");
        System.out.println("Se ha borrado ./directorioPadre");

        // ¿Existe el directorio "../directorioPadre"?
        System.out.println("¿Existe ../directorioPadre ? "
                + Files.exists(Paths.get("../directorioPadre")));

        copiarFicheros("./files/ficheroVacio.txt", "ficheroDestino.txt");

        // Listar directorio actual
        listarDirectorio(".");

        System.out.println("---------------");
        // Listar el directorio actual y subdirectorios
        listarTodo(".");
        // Mostrar recursivamente
        mostrarFicheros(new File("."));

    }

    // Crea un directorio según la ruta especificada
    // Si ya existe o no tiene permisos genera la excepción correspondiente
    // Si hay algún error en la ruta también genera una excepción
    public static void crearDirectorio(String ruta) {

        Path directory = Paths.get(ruta);
        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }

    }

    // Es igual que el método anterior pero crea los directorios intermedios
    // necesarios especificados en la ruta
    // Si el directorio a crear ya existe no hace nada
    public static void crearDirectorios(String ruta) {

        Path directory = Paths.get(ruta);
        try {
            Files.createDirectories(directory);
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }

    }

    // Este método genera un fichero vacío en la ruta especificada
    public static void crearFichero(String ruta) {
        Path file = Paths.get(ruta);
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (IOException e) {
            System.out.println("Problema creando el archivo " + ruta);
            System.out.println("Probablemente la ruta esté mal escrita.");
        }
    }

    // Este método borra ficheros y directorios de la ruta especificada
    // Si el directorio no está vacío no se borra
    public static void borrarElemento(String ruta) {
        Path file = Paths.get(ruta);
        try {
            Files.delete(file);
        } catch (NoSuchFileException nsfe) {
            System.out.println("No se puede borrar " + ruta + " porque no existe");
        } catch (DirectoryNotEmptyException dnee) {
            System.out.println("No se puede borrar el directorio porque no está vacío");
        } catch (IOException e) {
            System.out.println("Problema borrando el elemento " + ruta);
        }
    }

    // Copiado de ficheros
    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        try {
            Files.copy(origen, destino);
        } catch (IOException e) {
            System.out.println("Problema copiando el archivo.");
            System.out.println(e.toString());
        }
    }
//////    public static void copiarFicheros(String rutaOrigen, String rutaDestino) {
//////        Path origen = Paths.get(rutaOrigen);
//////        Path destino = Paths.get(rutaDestino);
//////        try {
//////            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
//////        } catch (IOException e) {
//////            System.out.println("Problema copiando el archivo.");
//////            System.out.println(e.toString());
//////        }
//////    }

    public static void listarDirectorio(String ruta) {

        File f = new File(ruta);
        if (f.exists()) {
            // Obtiene los ficheros y directorios dentro de f y los 
            // devuelve en un array
            File[] ficheros = f.listFiles();
            for (File file2 : ficheros) {
                System.out.println(file2.getName());
            }
        } else {
            System.out.println("El directorio a listar no existe");
        }
    }

    // Este método lista directorio especificado en ruta y los subdirectorios
    // Visita toda la jerarquía a partir del directorio "ruta"
    // Usa Api Stream 
    public static void listarTodo(String ruta) {

        try {
            Stream<Path> miStream = Files.walk(Paths.get(ruta));
            miStream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error listando directorios");
        }
    }

    // Método recursivo para mostrar archivos y carpetas
    public static void mostrarFicheros(File file) {
        List<File> ficheros = new ArrayList<>();
        List<File> carpetas = new ArrayList<>();

        String texto = file.isDirectory() ? "D - " + file.getName() : file.getName();
        System.out.println(texto);

        if (file.isDirectory()) { // Directorio - Tiene hijos
            File[] listaHijos = file.listFiles();
            // Por cada elemento separo en archivos y directorios
            for (File fichero : listaHijos) {
                if (fichero.isFile()) {
                    ficheros.add(fichero);
                } else {
                    carpetas.add(fichero);
                }
            }
            // Ordena la lista de ficheros por nombre
            ficheros.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
            // Muestra los nombres de los ficheros
            ficheros.forEach(f -> System.out.println("\t" + f.getName()));
            // Por cada carpeta, llama a la recursividad
            carpetas.forEach(c -> mostrarFicheros(c));
        }
    }

}
