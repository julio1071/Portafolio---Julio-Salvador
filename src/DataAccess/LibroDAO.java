package com.biblioteca.dao;

import com.biblioteca.modelo.Libro;

import java.util.List;

public interface LibroDAO {

    void guardar(Libro libro);

    Libro buscarPorIsbn(String isbn);

    List<Libro> listarTodos();

    void actualizarDisponibilidad(Libro libro);
}
