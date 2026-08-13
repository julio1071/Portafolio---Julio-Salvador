package com.biblioteca.controlador;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.dao.impl.LibroDAOImpl;
import com.biblioteca.dao.impl.PrestamoDAOImpl;
import com.biblioteca.dao.impl.UsuarioDAOImpl;
import com.biblioteca.excepciones.LibroNoDisponibleException;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;

import java.util.List;

public class GestorPrestamos {

    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    public GestorPrestamos() {
        this.libroDAO = new LibroDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();
        this.prestamoDAO = new PrestamoDAOImpl();
    }

    public void registrarLibro(Libro libro) {
        libroDAO.guardar(libro);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    public List<Libro> obtenerCatalogo() {
        return libroDAO.listarTodos();
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        return prestamoDAO.listarActivos();
    }

    public void prestarLibro(String isbn, String usuarioId, String fecha)
            throws LibroNoEncontradoException, LibroNoDisponibleException, UsuarioNoEncontradoException {

        Libro libro = libroDAO.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new LibroNoEncontradoException("No existe libro con ISBN " + isbn);
        }
        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException("\"" + libro.getTitulo() + "\" ya esta prestado.");
        }

        Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No existe usuario con ID " + usuarioId);
        }

        Prestamo prestamo = new Prestamo(libro, usuario, fecha);
        prestamoDAO.guardar(prestamo);
        libroDAO.actualizarDisponibilidad(libro);
    }

    public void devolverLibro(String isbn) throws LibroNoEncontradoException {
        Libro libro = libroDAO.buscarPorIsbn(isbn);
        if (libro == null) {
            throw new LibroNoEncontradoException("No existe libro con ISBN " + isbn);
        }
        libro.setDisponible(true);
        libroDAO.actualizarDisponibilidad(libro);
        prestamoDAO.finalizar(isbn);
    }
}
