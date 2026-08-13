package com.biblioteca.vista;

import com.biblioteca.controlador.GestorPrestamos;
import com.biblioteca.excepciones.LibroNoDisponibleException;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.PersistenciaException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;

import java.util.List;
import java.util.Scanner;

public class MenuConsola {

    private final GestorPrestamos gestor;
    private final Scanner scanner;

    public MenuConsola() {
        this.gestor = new GestorPrestamos();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=========================================");
        System.out.println("        Biblioteca Salvador S.A         ");
        System.out.println("=========================================");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Mostrar catalogo");
        System.out.println("4. Prestar libro");
        System.out.println("5. Devolver libro");
        System.out.println("6. Mostrar prestamos activos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarLibro();
                case 2 -> registrarUsuario();
                case 3 -> mostrarCatalogo();
                case 4 -> prestarLibro();
                case 5 -> devolverLibro();
                case 6 -> mostrarPrestamos();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } catch (LibroNoEncontradoException | LibroNoDisponibleException | UsuarioNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (PersistenciaException e) {
            System.out.println("ERROR DE BASE DE DATOS: " + e.getMessage());
        }
    }

    private void registrarLibro() {
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        gestor.registrarLibro(new Libro(titulo, autor, isbn));
        System.out.println("Libro registrado.");
    }

    private void registrarUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        gestor.registrarUsuario(new Usuario(nombre, id));
        System.out.println("Usuario registrado.");
    }

    private void mostrarCatalogo() {
        List<Libro> libros = gestor.obtenerCatalogo();
        if (libros.isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }
        for (Libro libro : libros) {
            System.out.println("  " + libro);
        }
    }

    private void prestarLibro() throws LibroNoEncontradoException, LibroNoDisponibleException, UsuarioNoEncontradoException {
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String usuarioId = scanner.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        gestor.prestarLibro(isbn, usuarioId, fecha);
        System.out.println("Prestamo registrado.");
    }

    private void devolverLibro() throws LibroNoEncontradoException {
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        gestor.devolverLibro(isbn);
        System.out.println("Devolucion registrada.");
    }

    private void mostrarPrestamos() {
        List<Prestamo> prestamos = gestor.obtenerPrestamosActivos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos activos.");
            return;
        }
        for (Prestamo prestamo : prestamos) {
            System.out.println("  " + prestamo);
        }
    }

    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
