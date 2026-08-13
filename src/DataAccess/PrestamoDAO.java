package com.biblioteca.dao;

import com.biblioteca.modelo.Prestamo;

import java.util.List;

public interface PrestamoDAO {

    void guardar(Prestamo prestamo);

    List<Prestamo> listarActivos();

    void finalizar(String isbn);
}
