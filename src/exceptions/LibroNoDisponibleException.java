package com.biblioteca.excepciones;

public class LibroNoDisponibleException extends Exception {

    public LibroNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
